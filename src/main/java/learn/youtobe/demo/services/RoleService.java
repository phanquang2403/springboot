package learn.youtobe.demo.services;

import learn.youtobe.demo.controllers.request.RoleByUserRequest;
import learn.youtobe.demo.controllers.request.RoleRequest;

public interface RoleService {



    /**
     * tạo quyền mới
     * @param
     * @return
     */
    Boolean createRole(RoleRequest request);

    /**
     * thêm quyền cho user
     * @param
     * @return
     */
    Boolean addRoleUser(RoleByUserRequest request);
}
