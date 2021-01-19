package com.eleven.filter;

import com.eleven.security.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/1/14 23:29
 * @apiNote 授权过滤器
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取用户成功权限信息
        UsernamePasswordAuthenticationToken authRequest = getAuthencation(request);
        //判断如果有前权限信息，则放到权限上下文中
        if(authRequest != null){
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthencation(HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader("token");
        if(token != null ){
            //从token中获取用户名
            String userName = tokenManager.getUserInfo(token);
            //从redis中获取用户信息
            List<String> permissionList = (List<String>) redisTemplate.opsForValue().get(userName);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            /*for (String permissionStr : permissionList) {
                SimpleGrantedAuthority simpleAuth = new SimpleGrantedAuthority(permissionStr);
                authorities.add(simpleAuth);
            }*/
            permissionList.stream().forEach(per -> {
                authorities.add(new SimpleGrantedAuthority(per));
            });
            return new UsernamePasswordAuthenticationToken(userName,token,authorities);
        }
        return null;

    }
}
