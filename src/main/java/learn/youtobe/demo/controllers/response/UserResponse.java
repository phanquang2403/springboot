package learn.youtobe.demo.controllers.response;

import learn.youtobe.demo.services.dtos.UserDTO;
import lombok.Data;

import java.util.List;



@Data
public class UserResponse {
    private List<UserDTO> list;
    Integer totalRecords;
}
