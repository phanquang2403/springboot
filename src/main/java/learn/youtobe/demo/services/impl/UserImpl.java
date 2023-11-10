package learn.youtobe.demo.services.impl;

import learn.youtobe.demo.base.BaseResponse;
import learn.youtobe.demo.base.CustomerException;
import learn.youtobe.demo.base.Message;
import learn.youtobe.demo.controllers.Request.InsertUserRequest;
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

    @Override
    public BaseResponse createAccount(InsertUserRequest request) throws CustomerException {
        if (request == null) {
            throw new CustomerException(Message.REQUEST_INVALID);
        }
        if (request.getEmail() == null) {
            throw new CustomerException("Email is required");
        }

        if (request.getUsername() == null) {
            throw new CustomerException("Username is required");
        }

        Boolean isExitsAccount = userDAO.isExitsAccount(request);
        System.out.println("isExitsAccount" + isExitsAccount);
        if (!userDAO.isExitsAccount(request)) {
            throw new CustomerException("username is exits");
        }

        return new BaseResponse(false, "OK", userDAO.createAccount(request));

    }
}
