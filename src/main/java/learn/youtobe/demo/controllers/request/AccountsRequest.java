package learn.youtobe.demo.controllers.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsRequest {
    private Integer page;
    private Integer pageSize;
}
