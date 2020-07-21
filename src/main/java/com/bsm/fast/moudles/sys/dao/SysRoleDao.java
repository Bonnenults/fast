package com.bsm.fast.moudles.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsm.fast.moudles.sys.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 角色(SysRole)表数据库访问层
 *
 * @author limingxin
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);

}