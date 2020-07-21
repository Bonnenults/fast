package com.bsm.fast.moudles.sys.model.param;

import lombok.Data;

/**
 * 登陆入参
 *
 * @author: limingxin
 * @time: 2020/7/21 0021 10:11
 */
@Data
public class LoginParam {

    private String username;

    private String password;

    private String captcha;

}
