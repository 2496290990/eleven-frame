package com.eleven.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojinhui
 * @date 2021/1/14 21:48
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

}
