package demo.controller;

import demo.exception.AppException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    //自定义【返回Json数据】的异常处理器
    /*@ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex){
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        if(ex instanceof AppException){
            map.put("when", ((AppException)ex).getWhen());
            map.put("how", ((AppException)ex).getHow());
        }
        return map;
    }*/

    //javax.servlet.error.status_code = 4xx, 5xx
    //自定义异常处理器【返回错误页面或者json数据】
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex, HttpServletRequest servletRequest){
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        if(ex instanceof AppException){
            map.put("when", ((AppException)ex).getWhen());
            map.put("how", ((AppException)ex).getHow());
        }
        servletRequest.setAttribute("moreErrors", map);
        return map;
    }
}
