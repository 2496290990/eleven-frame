package com.eleven.security;

import com.eleven.util.ResponseUtil;
import com.eleven.util.Result;
import com.eleven.util.ResultFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaojinhui
 * @date 2021/1/14 23:17
 * @apiNote
 */
public class UnauthHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(response, ResultFactory.fail("授权失败"));
    }
}
