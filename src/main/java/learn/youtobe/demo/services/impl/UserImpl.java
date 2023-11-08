package learn.youtobe.demo.services.impl;

import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.UserService;
import learn.youtobe.demo.services.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    public UserResponse getListAllAccount(UserRequest request) {
        return userDAO.getListAccount(request);
    }
}
