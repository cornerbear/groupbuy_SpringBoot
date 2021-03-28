package com.xj.groupbuy.controller.admin;


import com.xj.groupbuy.common.util.VerificationCode;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.service.IUserService;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;
    
    @RequestMapping("register")
    public CommonVO userRegister(String username, String password){
        //如果用户名存在，返回错误
        if (userService.loadUserByUsername(username) != null) {
            return new CommonVO(false,"用户名已存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        boolean save = userService.save(new User(username, password));
        return new CommonVO(save,save?"注册成功":"注册失败");
    }

    @GetMapping("login")
    public CommonVO login() {
        return CommonVO.error("尚未登录，请登录!");
    }

    @GetMapping("verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }
}
