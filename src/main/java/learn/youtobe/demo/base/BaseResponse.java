package learn.youtobe.demo.base;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    boolean error;
    String error_code;
    String message;
    Object data;

    public BaseResponse() {
    }

    public BaseResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public BaseResponse(boolean error, String message, Object data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(boolean error, String error_code, String message, Object data) {
        this.error = error;
        this.error_code = error_code;
        this.message = message;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
