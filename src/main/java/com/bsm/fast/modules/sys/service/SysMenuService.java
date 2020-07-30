package com.bsm.fast.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bsm.fast.modules.sys.model.entity.SysMenu;

import java.util.List;

/**
 * 菜单
 *
 * @author: limingxin
 */
public interface SysMenuService extends IService<SysMenu> {


    List<SysMenu> getUserMenuList(Long userId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);
}
