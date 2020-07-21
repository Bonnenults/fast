package com.bsm.fast.moudles.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsm.fast.moudles.sys.model.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理(SysMenu)表数据库访问层
 *
 * @author limingxin
 * @since 2020-07-20 10:28:40
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {

}