package learn.youtobe.demo.services.impl;

import learn.youtobe.demo.controllers.request.RoleByUserRequest;
import learn.youtobe.demo.controllers.request.RoleRequest;
import learn.youtobe.demo.services.RoleService;
import learn.youtobe.demo.services.daos.RoleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleImpl implements RoleService {

    private final RoleDAO roleDAO;
    @Override
    public Boolean createRole(RoleRequest request) {
        return roleDAO.createRole(request);
    }

    @Override
    public Boolean addRoleUser(RoleByUserRequest request) {
        return roleDAO.addRoleUser(request);
    }
}
