package com.eleven.config;

import com.eleven.filter.TokenAuthenticationFilter;
import com.eleven.filter.TokenLoginFilter;
import com.eleven.security.DefaultPasswordEncoder;
import com.eleven.security.TokenLogoutHandler;
import com.eleven.security.TokenManager;
import com.eleven.security.UnauthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author zhaojinhui
 * @date 2021/1/18 22:47
 * @apiNote
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DefaultPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                //没有权限就调用这个
                .authenticationEntryPoint(new UnauthHandler())
                //
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                //退出路径
                .and().logout().logoutUrl("/admin/acl/index.logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()
                //添加自定义过滤器
                .addFilter(new TokenAuthenticationFilter(authenticationManager()))
                .addFilter(new TokenLoginFilter(tokenManager, redisTemplate, authenticationManager()))
                .httpBasic();
    }

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * 不进行认证路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/api/**");
    }
}
