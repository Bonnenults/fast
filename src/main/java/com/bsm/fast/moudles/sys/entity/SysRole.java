package com.bsm.fast.moudles.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色(SysRole)实体类
 *
 * @author limingxin
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = -78619396176761305L;

    @TableId
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

}