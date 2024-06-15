package cn.chinatelecom.exceptions;

import cn.chinatelecom.utils.JSonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSonData<String> handle(Exception e){
        if(e instanceof BizException){
            BizException exception = (BizException)e;
            log.error("Business error {}",e);
            return JSonData.buildCodeAndMsg(exception.getCode(), exception.getMessage());
        }else{
            log.error("Global error: ",e);
            return JSonData.buildCodeAndMsg(-1,"Global error");
        }
    }
}
