package com.eleven.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eleven.util.Result;

/**
 * @author zhaojinhui
 * @date 2021/1/14 21:49
 * @apiNote
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(e);
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return new Result(e);
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Result error(GlobalException e){
        log.error(e.getMsg(), GlobalException.class);
        e.printStackTrace();
        return new Result(e);
    }



}
