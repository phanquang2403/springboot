package learn.youtobe.demo.config;

import learn.youtobe.demo.base.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Log4j2
@RestControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(
            MissingRequestHeaderException e) {
        String message = "Thiếu Header [" + e.getHeaderName() + "]";
        log.error(message);
        return ResponseEntity.ok(new BaseResponse(false, message, null));
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.ok(new BaseResponse(true, "Đã có lỗi xảy ra, vui lòng thử lại.", null));
    }
}
