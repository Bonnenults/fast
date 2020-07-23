package com.bsm.fast.modules.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置信息表(SysConfig)表实体类
 *
 * @author limingxin
 * @since 2020-07-20 10:50:31
 */
@Data
@TableName("sys_config")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    //key
    private String paramKey;

    //value
    private String paramValue;

    //状态   0：隐藏   1：显示
    private Object status;

    //备注
    private String remark;

}