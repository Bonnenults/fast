package com.bsm.fast.moudles.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsm.fast.moudles.sys.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系(SysUserRole)表数据库访问层
 *
 * @author limingixn
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);


    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}