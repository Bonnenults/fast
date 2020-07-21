package com.bsm.fast.moudles.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单管理(SysMenu)表实体类
 *
 * @author limingxin
 * @since 2020-07-20 10:28:40
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long menuId;

    //父菜单ID，一级菜单为0
    private Long parentId;

    //菜单名称
    private String name;

    //菜单URL
    private String url;

    //授权(多个用逗号分隔，如：user:list,user:create)
    private String perms;

    //类型   0：目录   1：菜单   2：按钮
    private Integer type;

    //菜单图标
    private String icon;

    //排序
    private Integer orderNum;

}