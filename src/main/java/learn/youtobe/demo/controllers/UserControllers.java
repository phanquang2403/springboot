package learn.youtobe.demo.controllers;

import learn.youtobe.demo.base.BaseController;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserControllers extends BaseController {
    private final UserService userService;

    private final JdbcTemplate jdbcTemplate;


    @PostMapping("/user")
    public ResponseEntity<?> getListUser(@RequestBody UserRequest request) {
        try {
            UserResponse result = userService.getListAllAccount(request);
            return successApi(result, "OK");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @GetMapping("/test-db-connection")
    public String testDbConnection() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database connection is successful!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}

