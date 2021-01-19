package com.eleven.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 * @author zhaojinhui
 * @date 2021/1/14 21:52
 * @apiNote
 */

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer SUCCESS = 1;

    private static final Integer FAIL = 0;

    private Object object;

    private Integer code;

    private T data;

    private String message;

    public Result(){

    }

    public Result(T data){
        this.data = data;
        this.message = "Success";
    }

    public Result(Throwable t){
        this.message = t.getMessage();
        this.code = FAIL;
    }

    public Result (T data ,String message,Object obj,Integer code){
        this.data = data;
        this.message = message;
        this.object = obj;
        this.code = code;
    }

    public Result error(String message){
        return new Result(null,message,null,FAIL);
    }
}
