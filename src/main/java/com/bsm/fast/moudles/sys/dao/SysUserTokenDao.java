package com.bsm.fast.moudles.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsm.fast.moudles.sys.model.entity.SysUserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token(SysUserToken)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-21 18:47:20
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserToken> {

}