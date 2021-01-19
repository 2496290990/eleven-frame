package com.eleven.security;

import cn.hutool.core.util.StrUtil;
import com.eleven.util.ResponseUtil;
import com.eleven.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaojinhui
 * @date 2021/1/14 23:03
 * @apiNote 用户退出并删除token信息
 */
public class TokenLogoutHandler implements LogoutHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    private TokenManager tokenManager;


    public TokenLogoutHandler(TokenManager tokenManager,RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.tokenManager = tokenManager;
    }

    /**
     * 用户退出
     * @param request 请求
     * @param response 响应
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //1.从header中获取token信息
        String token = request.getHeader("token");
        //2.如果token不为空 移除token。从redis中删除token
        if(StrUtil.isNotEmpty(token)){
            tokenManager.removeToken(token);

            //从token中获取用户信息
            String userName = tokenManager.getUserInfo(token);
            //从redis中删除用户信息
            redisTemplate.delete(userName);
        }

        ResponseUtil.out(response, new Result());
    }
}
