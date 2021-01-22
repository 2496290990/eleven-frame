package com.javacode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.entity.SecurityUser;
import com.javacode.entity.Permission;
import com.javacode.mapper.PermissionMapper;
import com.javacode.mapper.UserRoleMapper;
import com.javacode.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhaojinhui
 * @date 2021/1/19 22:10
 * @apiNote
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Set<String> selectPermissionByUserId(String id) {
        return permissionMapper.selectAllPermissionByUserId(id);
    }


}
