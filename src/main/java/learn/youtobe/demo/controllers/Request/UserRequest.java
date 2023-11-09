package learn.youtobe.demo.controllers.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest  {
    private String name;
    private Integer page;
    private Integer pageSize;
}
