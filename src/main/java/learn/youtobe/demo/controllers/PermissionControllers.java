package learn.youtobe.demo.controllers;


import learn.youtobe.demo.base.BaseController;
import learn.youtobe.demo.controllers.request.ModuleActionRequest;
import learn.youtobe.demo.controllers.request.ModuleRoleRequest;
import learn.youtobe.demo.controllers.request.PermissionRequest;
import learn.youtobe.demo.controllers.request.RoleRequest;
import learn.youtobe.demo.controllers.response.RoleResponse;
import learn.youtobe.demo.services.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/permission")
public class PermissionControllers extends BaseController {
    private final PermissionService permissionService;

    @PostMapping("/create-module")
    public ResponseEntity<?> createModule(@RequestBody PermissionRequest request) {
        try {
            Boolean execute = permissionService.createModule(request);
            if (execute) {
                return successApi(null, "Tạo thành công");
            }
            return errorApi("Tạo không thành công");
        } catch (Exception e) {
            log.info("createModule_error {0}", e.getMessage());
            return errorApi(e.getLocalizedMessage());
        }
    }

    @PostMapping("/create-module-role")
    public ResponseEntity<?> createModuleRole(@RequestBody ModuleRoleRequest request) {
        try {
            Boolean execute = permissionService.createModuleRole(request);
            if (execute) {
                return successApi(null, "Tạo thành công");
            }
            return errorApi("Tạo không thành công");
        } catch (Exception e) {
            log.error("createModuleRole_error {0}", e.getLocalizedMessage());
            return errorApi(e.getLocalizedMessage());
        }
    }

    @PostMapping("/create-module-action")
    public ResponseEntity<?> createModuleAction(@RequestBody ModuleActionRequest request) {
        try {
            Boolean execute = permissionService.createModuleAction(request);
            if (execute) {
                return successApi(null, "Tạo thành công");
            }
            return errorApi("Tạo không thành công");
        } catch (Exception e) {
            log.error("createModuleAction_error {0}", e.getLocalizedMessage());
            return errorApi(e.getLocalizedMessage());
        }
    }

    @PostMapping("/module")
    public ResponseEntity<?> getPermission(@RequestBody RoleRequest request) {
        try {
            List<RoleResponse> result = permissionService.getPermission(request);
            return successApi(result, "ok");
        } catch (Exception e) {
            log.info("getPermission_error {0}", e.getLocalizedMessage());
            errorApi(e.getLocalizedMessage());
        }
        return null;
    }
}
