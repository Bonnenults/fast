package com.bsm.fast.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsm.fast.common.constant.Constants;
import com.bsm.fast.modules.sys.dao.SysMenuDao;
import com.bsm.fast.modules.sys.model.entity.SysMenu;
import com.bsm.fast.modules.sys.service.SysMenuService;
import com.bsm.fast.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysMenu> getUserMenuList(Long userId) {
        if (Constants.SUPER_ADMIN == userId) {
            return getAllMenuList(null);
        }
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);

        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    public List<SysMenu> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0L, menuIdList);
        getMenuTreeList(menuList,menuIdList);

        return menuList;
    }

    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(Long parentId) {
        return this.list(new QueryWrapper<SysMenu>().eq("parent_id", parentId).orderByAsc("order_num"));
    }

    /**
     * 递归获取菜单目录
     *
     * @param menuList
     * @param menuIdList
     * @return
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<>();

        for (SysMenu entity : menuList) {
            //目录
            if (entity.getType() == Constants.MenuType.CATALOG.getValue()) {
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

}
