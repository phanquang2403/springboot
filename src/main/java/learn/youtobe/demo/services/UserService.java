package learn.youtobe.demo.services;

import org.springframework.core.io.Resource;
import learn.youtobe.demo.base.BaseResponse;
import learn.youtobe.demo.base.CustomerException;
import learn.youtobe.demo.controllers.Request.InsertUserRequest;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponse getListAllAccount(UserRequest request);

    BaseResponse createAccount(InsertUserRequest request) throws CustomerException;

    byte[] exportExcel(Resource resource,UserRequest request);
}
