package cn.chinatelecom.exceptions;

import cn.chinatelecom.enums.BizCodeEnum;
import lombok.Data;

@Data
public class BizException extends RuntimeException {
    private int code;
    private String message;

    public BizException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.message = bizCodeEnum.getMessage();
    }

    public BizException(){
        super();
    }
}
