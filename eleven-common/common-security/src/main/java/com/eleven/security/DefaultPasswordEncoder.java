package com.eleven.security;

import com.eleven.util.MD5;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhaojinhui
 * @date 2021/1/14 22:40
 * @apiNote 密码处理工具类
 */
public class DefaultPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encode(charSequence.toString());

    }

    @Override
    public boolean matches(CharSequence charSequence, String encode) {
        return encode.equals(MD5.encode(charSequence.toString()));
    }
}
