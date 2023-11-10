package learn.youtobe.demo.config;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import learn.youtobe.demo.base.BaseResponse;
import learn.youtobe.demo.base.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<BaseResponse> handleException(Exception ex) {
        return handleMessageException(ex);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<BaseResponse> handleException(CustomerException ex) {
        return handleMessageException(ex);
    }

    private ResponseEntity<BaseResponse> handleMessageException(Exception ex) {
        if (ex instanceof CustomerException) {
            return processPopulateException((CustomerException) ex);
        } else {
            HttpStatus status = HttpStatus.OK;
            BaseResponse errorDetails = new BaseResponse();
            errorDetails.setError(true);
            if (ex instanceof HttpRequestMethodNotSupportedException || ex instanceof MissingServletRequestParameterException) {
                status = HttpStatus.BAD_REQUEST;
                if (ex.getLocalizedMessage() != null && ex.getLocalizedMessage().contains("Required")) {
                    errorDetails.setMessage("Thiếu tham số");
                } else {
                    errorDetails.setMessage("Sai phương thức");
                }
            } else if (ex instanceof HttpMessageConversionException || ex instanceof HttpMediaTypeNotSupportedException || ex instanceof HttpMessageNotReadableException || ex instanceof InvalidFormatException) {
                logger.error(ex.getLocalizedMessage());
                status = HttpStatus.BAD_REQUEST;
                errorDetails.setMessage("Sai định dạng dữ liệu");
            } else if (ex instanceof MethodArgumentNotValidException) {
                StringBuilder message = new StringBuilder();
                BindingResult bindingresult = ((MethodArgumentNotValidException) ex).getBindingResult();
                List<FieldError> fieldErrors = bindingresult.getFieldErrors();
                for (FieldError error : fieldErrors) {
                    message.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(";");
                }
                errorDetails.setMessage(message.toString());
            } else {
                if (ex instanceof SQLException) {
                    logger.error(ex.getLocalizedMessage());
                } else {
                    logger.error(ex.getLocalizedMessage(), ex);
                }
                errorDetails.setMessage("Hệ thống đang bận vui lòng thử lại sau");
            }
            return new ResponseEntity<>(errorDetails, status);
        }
    }

    private ResponseEntity<BaseResponse> processPopulateException(CustomerException ex) {
        if (ex.getCode() == 401) {
            BaseResponse errorDetails = new BaseResponse();
            errorDetails.setError(true);
            errorDetails.setMessage("Tài khoản đã được đăng nhập ở một nơi khác");
            return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
        } else {
            BaseResponse errorDetails = new BaseResponse();
            errorDetails.setError(true);
            errorDetails.setMessage(ex.getLocalizedMessage());
            return new ResponseEntity<>(errorDetails, HttpStatus.OK);
        }
    }
}
