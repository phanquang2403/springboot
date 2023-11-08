package learn.youtobe.demo.base;

import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity<BaseResponse> successApi(Object data, String message) {
        return ResponseEntity.ok(new BaseResponse(false, message, data));
    }
}
