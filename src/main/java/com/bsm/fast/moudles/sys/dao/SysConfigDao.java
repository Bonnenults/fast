package com.bsm.fast.moudles.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsm.fast.moudles.sys.model.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置信息表(SysConfig)表数据库访问层
 *
 * @author limingxin
 * @since 2020-07-20 10:50:31
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfig> {

}