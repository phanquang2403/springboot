package learn.youtobe.demo.controllers;

import learn.youtobe.demo.base.BaseController;
import learn.youtobe.demo.base.BaseResponse;
import learn.youtobe.demo.base.CustomerException;
import learn.youtobe.demo.controllers.Request.InsertUserRequest;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import org.springframework.core.io.Resource;

@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserControllers extends BaseController {
    private final UserService userService;

    private final JdbcTemplate jdbcTemplate;

    @Value(value = "classpath:/TemplateExcel/TEMP_USER.xlsx")
    Resource resourceExcelUser;


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

    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody InsertUserRequest request) throws CustomerException {
        try{
            BaseResponse result = userService.createAccount(request);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (CustomerException e){
            throw new CustomerException(e.getMessage());
        }catch (Exception e){
            log.error("createUser_error {0}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("export-excel-user")
    public ResponseEntity<?> exportExcelUser(@RequestBody UserRequest request) throws CustomerException {
        try{
            Date data = new Date();
            String fileName = "bao_cao_"+data.getTime() + ".xlsx";
            Resource resource = resourceExcelUser;
            byte[] createFileExcel = userService.exportExcel(resource,request);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .body(createFileExcel);
        } catch (Exception e){
            log.error("exportExcelUser_error {0}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

