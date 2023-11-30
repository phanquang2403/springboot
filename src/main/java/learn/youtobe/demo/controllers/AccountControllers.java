package learn.youtobe.demo.controllers;

import learn.youtobe.demo.base.BaseController;
import learn.youtobe.demo.controllers.request.AccountsRequest;
import learn.youtobe.demo.controllers.response.AccountResponse;
import learn.youtobe.demo.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountControllers extends BaseController {
    private final AccountService accountService;

    @PostMapping("/getAll")
    public ResponseEntity<?> getAllAccount(@RequestBody AccountsRequest request) {
        try {
            AccountResponse result = accountService.getAllAccount(request);
            return successApi(result, "ok");
        } catch (Exception e) {
            log.error("getAllAccount_error {0}", e.getLocalizedMessage());
            return errorApi(e.getMessage());
        }
    }
}
