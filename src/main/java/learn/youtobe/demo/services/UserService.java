package learn.youtobe.demo.services;

import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;

public interface UserService {
    UserResponse getListAllAccount(UserRequest request);
}
