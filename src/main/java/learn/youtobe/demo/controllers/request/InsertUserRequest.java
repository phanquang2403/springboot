package learn.youtobe.demo.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertUserRequest implements Serializable {
    private String username;
    private String email;
    private  String pass;
}
