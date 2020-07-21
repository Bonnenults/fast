package com.bsm.fast.moudles.sys.controller;

import com.bsm.fast.common.domain.R;
import com.bsm.fast.common.utils.ShiroUtils;
import com.bsm.fast.moudles.sys.model.param.LoginParam;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @PostMapping(value = "/login")
    public R login(@RequestBody LoginParam param) {
        String code = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!code.equals(param.getCaptcha())) {
            return R.error("验证码不正确");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e){
            return R.error("账户或密码不正确");
        } catch (LockedAccountException e) {
            return R.error("账户被锁定，请联系管理员");
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }

        return R.ok();
    }
}
