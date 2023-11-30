package learn.youtobe.demo.services.impl;

import jakarta.transaction.Transactional;
import learn.youtobe.demo.controllers.request.ModuleActionRequest;
import learn.youtobe.demo.controllers.request.ModuleRoleRequest;
import learn.youtobe.demo.controllers.request.PermissionRequest;
import learn.youtobe.demo.controllers.request.RoleRequest;
import learn.youtobe.demo.controllers.response.RoleResponse;
import learn.youtobe.demo.services.PermissionService;
import learn.youtobe.demo.services.daos.PermissionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionImpl implements PermissionService {

    private final PermissionDAO permissionDAO;

    @Override
    @Transactional
    public Boolean createModule(PermissionRequest request) throws Exception {
        Boolean isExits = permissionDAO.ExitsModuleCode(request.getModuleCode());
        if (isExits) {
            throw new Exception("ModuleCode này đã tồn tại");
        }
        return permissionDAO.createModule(request);
    }


    @Override
    @Transactional
    public Boolean createModuleRole(ModuleRoleRequest request) throws Exception {
        Boolean isExits = permissionDAO.ExitsModuleRoleCode(request.getRoleCode());
        if (isExits) {
            throw new Exception("RoleCode này đã tồn tại");
        }
        return permissionDAO.createModuleRole(request);
    }

    @Override
    @Transactional
    public Boolean createModuleAction(ModuleActionRequest request) throws Exception {
        if (request.getIdModuleRole() == null) {
            throw new Exception("Không bỏ trống idModuleRole");
        }

        if (request.getAction().isEmpty()) {
            throw new Exception("Không bỏ trống action");
        }

        Boolean isExitsActionInRole = permissionDAO.ExitsModuleRoleAction(request.getAction(), request.getIdModuleRole());
        if (isExitsActionInRole) {
            throw new Exception("action này đã được tạo trước đó");
        }
        return permissionDAO.AddRoleAction(request);
    }

    @Override
    public List<RoleResponse> getPermission(RoleRequest request) throws Exception {
        if (request.getAppId() == null && request.getUsername() == null) {
            throw new Exception("appId và username không bỏ trống!");
        }
        if (request.getUsername() == null) {
            throw new Exception("username không bỏ trống!");
        }

        if (request.getAppId() == null) {
            throw new Exception("appId không bỏ trống!");
        }

        List<RoleResponse> result = permissionDAO.getPermission(request.getAppId());
        Map<Integer, List<RoleResponse>> groupModuleByRoleId = result.stream().collect(Collectors.groupingBy(RoleResponse::getModuleRoleId));
        List<RoleResponse> mapResult = new ArrayList<>();

        if(groupModuleByRoleId.isEmpty()){
            return  null;
        }

        for (Map.Entry<Integer, List<RoleResponse>> entry : groupModuleByRoleId.entrySet()) {
            List<String> listAction = entry.getValue().stream().map(RoleResponse::getAction).toList();
            RoleResponse newRole = entry.getValue().get(0);
            if (newRole != null) {
                newRole.setActions(listAction);
                mapResult.add(newRole);
            }
        }
        return mapResult;
    }
}
