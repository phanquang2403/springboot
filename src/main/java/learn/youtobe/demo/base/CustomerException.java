package learn.youtobe.demo.base;

import lombok.Data;
@Data
public class CustomerException extends  Exception{
    private int code;
    private String message;
    private Object data;

    public  CustomerException(int code,String message){
        this.code = code;
        this.message = message;
    }

    public  CustomerException(String message){
        this.code  = 500;
        this.message= message;
    }


    public boolean verifyError(String errorCode) {
        return code > 0;
    }

    public void setData(Object data) {
        this.data = data;
    }



    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
