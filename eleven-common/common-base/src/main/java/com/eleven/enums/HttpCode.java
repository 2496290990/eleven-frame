package com.eleven.enums;

import lombok.Data;

/**
 * @author zhaojinhui
 * @date 2021/1/14 23:23
 * @apiNote
 */
public enum HttpCode {

    /** 成功 */
    SUCCESS(200),
    /** 资源未找到*/
    NOT_FOUND(404),
    /** 资源重定向*/
    REDIRECT(303),
    /** 请求失败 */
    FAIL(500),
    /** 请求支持 */
    UN_SUPPORT(415)
    ;

    private Integer code;

    private HttpCode(Integer code){
        this.code = code;
    }

    public Integer code(){
        return code;
    }

}
