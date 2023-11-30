package learn.youtobe.demo.controllers.response;

import learn.youtobe.demo.services.dtos.AccountDTO;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {
    private List<AccountDTO> list;
    private  Integer totalRecords;
}
