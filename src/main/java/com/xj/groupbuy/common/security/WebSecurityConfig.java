package com.xj.groupbuy.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xj.groupbuy.background.user.entity.User;
import com.xj.groupbuy.background.user.service.IUserService;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/11
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    IUserService userService;
//    @Autowired
//    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
//    @Autowired
//    CustomUrlDecisionManager customUrlDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
//    }
//
//    @Bean
//    LoginFilter loginFilter() throws Exception {
//        LoginFilter loginFilter = new LoginFilter();
//        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
//                    User user = (User) authentication.getPrincipal();
//                    user.setPassword(null);
//                    CommonVO ok = CommonVO.ok("登录成功!", user);
//                    String s = new ObjectMapper().writeValueAsString(ok);
//                    out.write(s);
//                    out.flush();
//                    out.close();
//                }
//        );
//        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
//                    CommonVO respBean = CommonVO.error(exception.getMessage());
//                    if (exception instanceof LockedException) {
//                        respBean.setMsg("账户被锁定，请联系管理员!");
//                    } else if (exception instanceof CredentialsExpiredException) {
//                        respBean.setMsg("密码过期，请联系管理员!");
//                    } else if (exception instanceof AccountExpiredException) {
//                        respBean.setMsg("账户过期，请联系管理员!");
//                    } else if (exception instanceof DisabledException) {
//                        respBean.setMsg("账户被禁用，请联系管理员!");
//                    } else if (exception instanceof BadCredentialsException) {
//                        respBean.setMsg("用户名或者密码输入错误，请重新输入!");
//                    }
//                    out.write(new ObjectMapper().writeValueAsString(respBean));
//                    out.flush();
//                    out.close();
//                }
//        );
//        loginFilter.setAuthenticationManager(authenticationManagerBean());
//        loginFilter.setFilterProcessesUrl("/doLogin");
//        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
//        sessionStrategy.setMaximumSessions(1);
//        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
//        return loginFilter;
//    }
//
//    @Bean
//    SessionRegistryImpl sessionRegistry() {
//        return new SessionRegistryImpl();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    // 登录成功
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        User user = (User)authentication.getPrincipal();
                        CommonVO ok = CommonVO.ok("登录成功!", user);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();

                        CommonVO commonVO = CommonVO.error(exception.getMessage());
                        if (exception instanceof LockedException) {
                            commonVO.setMsg("账户被锁定，请联系管理员!");
                        } else if (exception instanceof CredentialsExpiredException) {
                            commonVO.setMsg("密码过期，请联系管理员!");
                        } else if (exception instanceof AccountExpiredException) {
                            commonVO.setMsg("账户过期，请联系管理员!");
                        } else if (exception instanceof DisabledException) {
                            commonVO.setMsg("账户被禁用，请联系管理员!");
                        } else if (exception instanceof BadCredentialsException) {
                            commonVO.setMsg("用户名或者密码输入错误，请重新输入!");
                        }
                        
                        String s = new ObjectMapper().writeValueAsString(commonVO);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        
                    }
                })
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }
}
