package learn.youtobe.demo.services;

import learn.youtobe.demo.services.dtos.UserDTO;
import org.springframework.core.io.Resource;
import learn.youtobe.demo.base.BaseResponse;
import learn.youtobe.demo.base.CustomerException;
import learn.youtobe.demo.controllers.Request.InsertUserRequest;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UserService {
    UserResponse getListAllAccount(UserRequest request);

    Boolean createAccount(InsertUserRequest request) throws CustomerException;

    byte[] exportExcel(Resource resource,UserRequest request);

    List<UserDTO> importExcel(MultipartFile file);
}
