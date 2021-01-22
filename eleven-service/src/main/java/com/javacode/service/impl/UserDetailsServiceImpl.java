package com.javacode.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eleven.entity.SecurityUser;
import com.javacode.entity.User;
import com.javacode.mapper.PermissionMapper;
import com.javacode.mapper.UserMapper;
import com.javacode.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaojinhui
 * @date 2021/1/19 21:19
 * @apiNote
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionService permissionService;

    /**
     * 根据用户名查询数据库
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if(null == user){
            throw new UsernameNotFoundException(username + "用户不存在");
        }
        org.springframework.security.core.userdetails.User currentUser = BeanUtil.copyProperties(user, org.springframework.security.core.userdetails.User.class);

        //根据用户id获取用户权限列表
        Set<String> permissions = permissionService.selectPermissionByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        List<String> permissionList = securityUser.getPermissionList();
        permissionList.addAll(permissions);
        securityUser.setPermissionList(permissionList);
        return securityUser;
    }
}
