package com.eleven.util;

import com.eleven.enums.HttpCode;

import java.sql.ResultSet;

/**
 * @author zhaojinhui
 * @date 2021/1/14 23:22
 * @apiNote
 */
public class ResultFactory<T> {


    public static Result success(Object data){
        return new Result(data, "success", null, HttpCode.SUCCESS.code());
    }

    public static Result fail(String message){
        return new Result(null,message,null,HttpCode.FAIL.code());
    }


}
