package learn.youtobe.demo.controllers.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRoleRequest {
    private String username;
    private String phoneNumber;
}
