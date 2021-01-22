package com.javacode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javacode.entity.Permission;

import java.util.Set;

/**
 * @author zhaojinhui
 * @date 2021/1/19 21:24
 * @apiNote
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据用户id查询所有的权限信息
     * @param id
     * @return
     */
    Set<String> selectAllPermissionByUserId(String id);
}
