package com.javacode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javacode.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * @author zhaojinhui
 * @date 2021/1/19 22:09
 * @apiNote
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 根据用户id查询权限列表
     * @param id
     * @return
     */
    Set<String> selectPermissionByUserId(String id);
}
