package learn.youtobe.demo.services;


import learn.youtobe.demo.controllers.request.ModuleActionRequest;
import learn.youtobe.demo.controllers.request.ModuleRoleRequest;
import learn.youtobe.demo.controllers.request.PermissionRequest;
import learn.youtobe.demo.controllers.request.RoleByUserRequest;
import learn.youtobe.demo.controllers.response.RoleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    public Boolean createModule(PermissionRequest request) throws Exception;
    public Boolean createModuleRole(ModuleRoleRequest request) throws Exception;
    public Boolean createModuleAction(ModuleActionRequest request) throws Exception;
    public List<RoleResponse> getPermission(RoleByUserRequest request) throws Exception;
}
