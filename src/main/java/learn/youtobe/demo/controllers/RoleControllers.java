package learn.youtobe.demo.controllers;

import learn.youtobe.demo.base.BaseController;
import learn.youtobe.demo.controllers.request.AddUserRoleRequest;
import learn.youtobe.demo.controllers.request.RoleRequest;
import learn.youtobe.demo.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleControllers extends BaseController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest) {
        try {
            Boolean execute = roleService.createRole(roleRequest);
            if (execute) {
                return successApi(null, "Tạo thành công");
            }
            return errorApi("Tạo không thành công");
        } catch (Exception e) {
            log.error("createRole_error" + e.getMessage());
            return errorApi(e.getMessage());
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addRoleUser(AddUserRoleRequest request) {
        try {
            return successApi("null", "Thêm quyền cho user thành công");

        } catch (Exception e) {
            log.error("addRoleUser_error" + e.getMessage());
            return errorApi(e.getMessage());
        }
    }
}
