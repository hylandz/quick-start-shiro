package com.xlx.admin.controller.system;

import com.xlx.common.dto.LoginDTO;
import com.xlx.common.dto.Result;
import com.xlx.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * login
 *
 * @author xielx at 2020/4/22 15:39
 */
@Controller
@Slf4j
public class LoginController extends BaseController{


    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @PostMapping("/login")
    @ResponseBody
    public Result<Object> login(LoginDTO loginDTO){
    
        UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(),loginDTO.getPassword(),loginDTO.getRememberMe());
        Subject subject = getSubject();
        try{
            subject.login(token);
            return Result.ok().message("登录成功");
        }catch (CredentialsException cre){
            throw new CredentialsException("用户名或密码错误");
        }catch (AuthenticationException auth){
            throw new AuthenticationException("用户名或密码错误");
        }
        
    }
}
