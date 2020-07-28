package com.bsm.fast.modules.sys.controller;

import com.bsm.fast.common.domain.R;
import com.bsm.fast.common.utils.ShiroUtils;
import com.bsm.fast.modules.sys.model.param.LoginParam;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/sys")
public class SysLoginController {

    @Resource
    private Producer producer;

    /**
     * 图片验证码
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);

    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public R login(String username, String password, String captcha) {
        String code = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!code.equals(captcha)) {
            return R.error("验证码不正确");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            log.info("username:{},password:{}", username, password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            log.info("token:{}", token.toString());
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error("账户或密码不正确");
        } catch (LockedAccountException e) {
            return R.error("账户被锁定，请联系管理员");
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }

        return R.ok();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public R logout() {
        ShiroUtils.logout();
        return R.ok();
    }
}
