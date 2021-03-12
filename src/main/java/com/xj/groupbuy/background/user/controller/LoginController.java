package com.xj.groupbuy.background.user.controller;

import com.xj.groupbuy.common.util.VerificationCode;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/11
 */
@RestController
public class LoginController {

<<<<<<< Updated upstream
=======
    @GetMapping("/login")
    public CommonVO login() {
        return CommonVO.error("尚未登录，请登录!");
    }
    
>>>>>>> Stashed changes

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }
}
