package learn.youtobe.demo.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleByUserRequest {
    private String username;
    private Integer appId;
}
