package learn.youtobe.demo.services.impl;

import learn.youtobe.demo.controllers.request.AccountsRequest;
import learn.youtobe.demo.controllers.response.AccountResponse;
import learn.youtobe.demo.services.AccountService;
import learn.youtobe.demo.services.daos.AccountDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountImpl implements AccountService {

    private final AccountDAO accountDAO;
    @Override
    public AccountResponse getAllAccount(AccountsRequest request) {
        return accountDAO.getAllAccount(request);
    }
}
