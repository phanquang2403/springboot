package learn.youtobe.demo.controllers.Request;

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
