package com.bsm.fast.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsm.fast.modules.sys.model.entity.SysUserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token(SysUserToken)表数据库访问层
 *
 * @author limingxin
 * @since 2020-07-21 18:47:20
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserToken> {

}