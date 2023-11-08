package learn.youtobe.demo.controllers;

import learn.youtobe.demo.base.BaseController;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class userControllers extends BaseController {
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getListUser(@RequestParam UserRequest request){
        try{
            UserResponse result = userService.getListAllAccount(request);
            return  successApi(result,"OK");
        }
        catch (Exception  e){
            log.error(e.getMessage());
        }
        return null;
    }
}
