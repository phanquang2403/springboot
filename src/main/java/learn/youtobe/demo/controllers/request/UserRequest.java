package learn.youtobe.demo.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {
    private String name;
    private Integer page;
    private Integer pageSize;
}
