package learn.youtobe.demo.base;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity<BaseResponse> successApi(Object data, String message) {
        return ResponseEntity.ok(new BaseResponse(false, message, data));
    }

    protected ResponseEntity<BaseResponse> errorApi(String message) {
        if (StringUtils.isNotEmpty(message) ) {
            return ResponseEntity.ok(new BaseResponse(true, message.replaceAll("com.viettelpost.core.base.VtException:", ""), null));
        }
        return ResponseEntity.ok(new BaseResponse(true, message, null));
    }
}
