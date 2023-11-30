package learn.youtobe.demo.services.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer userId;
    private String username;
    private String phone;
    private String cccd;
    private String email;
    private String address;
    private String fullname;

}
