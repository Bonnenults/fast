package com.bsm.fast.moudles.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系(SysUserRole)实体类
 *
 * @author makejava
 */
@Data
@TableName()
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

}