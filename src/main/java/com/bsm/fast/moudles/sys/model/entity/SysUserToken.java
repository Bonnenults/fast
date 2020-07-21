package com.bsm.fast.moudles.sys.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户Token
 *
 * @author: limingxin
 * @time: 2020/7/21 0021 10:11
 */
@Data
@TableName("sys_user_token")
public class SysUserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户ID
    @TableId(type = IdType.INPUT)
    private Long userId;

    //token
    private String token;

    //过期时间
    private Date expireTime;

    //更新时间
    private Date updateTime;

}