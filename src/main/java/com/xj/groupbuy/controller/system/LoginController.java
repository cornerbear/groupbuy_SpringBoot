package com.xj.groupbuy.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.util.VerificationCode;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.entity.UserRole;
import com.xj.groupbuy.service.ILoginService;
import com.xj.groupbuy.service.IRoleService;
import com.xj.groupbuy.service.IUserRoleService;
import com.xj.groupbuy.service.IUserService;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

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
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ILoginService loginService;
    
    @GetMapping("checkLogin")
    public CommonVO checkLogin(HttpServletRequest request,HttpServletResponse response){
        return loginService.checkLogin(request,response);
    }
    
    @PostMapping("register")
    public CommonVO userRegister(@RequestBody Map<String,String> data,HttpServletRequest request){

        String verify_code = (String) request.getSession().getAttribute("verify_code");
        String username = data.get("username");
        String password = data.get("password");
        String code = data.get("code");

//        if(!code.equals(verify_code)){
//            return new CommonVO(false,"验证码不正确");
//        }
        //如果用户名存在，返回错误
        if (userService.checkUser(username) != 0) {
            return new CommonVO(false,"用户名已存在");
        }
        String encode = passwordEncoder.encode(password);
        User user = new User(username, encode);
        boolean save = userService.save(user);
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("name", "ROLE_USER"));
        UserRole userRole = new UserRole(user.getUserId(), role.getRoleId());
        boolean save1 = userRoleService.save(userRole);

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
