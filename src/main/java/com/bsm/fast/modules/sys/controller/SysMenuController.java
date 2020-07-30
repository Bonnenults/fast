package com.bsm.fast.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.bsm.fast.common.domain.R;
import com.bsm.fast.modules.sys.model.entity.SysMenu;
import com.bsm.fast.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单
 *
 * @author: limingxin
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public R nav() {
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        JSONObject menuJson = new JSONObject();
        menuJson.put("menuList",menuList);
        return R.ok(menuJson);
    }

}
