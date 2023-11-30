package learn.youtobe.demo.services;


import learn.youtobe.demo.controllers.request.AccountsRequest;
import learn.youtobe.demo.controllers.response.AccountResponse;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public AccountResponse getAllAccount(AccountsRequest request);

}
