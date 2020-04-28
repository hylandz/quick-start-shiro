package com.xlx.admin.controller.system;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xlx.common.dto.Result;
import com.xlx.common.util.DateTimeUtil;
import com.xlx.common.util.FileUtil;
import com.xlx.framework.util.ShiroUtil;
import com.xlx.system.entity.User;
import com.xlx.system.service.UserService;
import com.xlx.system.vo.ProfileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
@Controller
@RequestMapping("/system/user")
@Slf4j
public class UserController {
    
    @Value("${quick-start-shiro.base-dir}")
    private String baseDir;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/profile")
    public ModelAndView profileHtml() {
        ModelAndView mav = new ModelAndView("system/user/profile/profile");
        User currentUser = ShiroUtil.getCurrentUser();
        ProfileVO profile = userService.findProfile(currentUser);
        log.info("profile:{}", JSON.toJSONString(profile));
        mav.addObject("user", profile);
        return mav;
    }
    
    
    @GetMapping("/profile/edit/{userId}")
    public String getProfile(@PathVariable Long userId, Model model) {
        User currentUser = ShiroUtil.getCurrentUser();
        ProfileVO profile = userService.findProfile(currentUser);
        model.addAttribute("user", profile);
        return "system/user/profile/edit";
    }
    
    @GetMapping("/profile/avatar")
    public String updateAvatarHtml(Model model){
        User currentUser = ShiroUtil.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "system/user/profile/avatar";
    }
    
    @GetMapping("/profile/resetPwd")
    public String reSetPasswordHtml(Model model) {
        User currentUser = ShiroUtil.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "system/user/profile/resetPwd";
    }
    
    @PostMapping("/profile/update")
    @ResponseBody
    public Result<Object> editProfile(User profile) {
        boolean update = userService.update(profile, new UpdateWrapper<User>().eq("user_id",profile.getUserId()));
        return update ? Result.ok() : Result.error();
    }
    
    
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public Result<Object> checkEmailUnique(User user) {
        User one = userService.getOne(new QueryWrapper<User>().eq("user_id", user.getUserId()).eq("email", user.getEmail()));
        return one != null ? Result.error().message("邮箱不可用").data(1) : Result.ok().message("邮箱可用").data(0);
    }
    
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public Result<Object> checkPhoneUnique(User user) {
        User one = userService.getOne(new QueryWrapper<User>().eq("user_id", user.getUserId()).eq("phone", user.getPhone()));
        return one != null ? Result.error().message("手机号存在") : Result.ok().message("手机号可用");
    }
    
    
    
    @GetMapping("/profile/checkPassword")
    @ResponseBody
    public Result<Object> checkPassword(@NotNull String oldPassword) {
        log.info("旧密码:{}",oldPassword);
        User currentUser = ShiroUtil.getCurrentUser();
        String password = currentUser.getPassword();
        String encryptPassword = ShiroUtil.encryptPassword(oldPassword, currentUser.getCredentials());
        return Objects.equals(password, encryptPassword) ? Result.ok().message("原密码输入正确") : Result.error().message("原密码输入不正确");
    }
    @PostMapping("/profile/resetPwd")
    @ResponseBody
    public Result<Object> resetPassword(@NotNull String password) {
        User currentUser = ShiroUtil.getCurrentUser();
        currentUser.setSalt(ShiroUtil.generateHexRandomNumber());
        String encryptPassword = ShiroUtil.encryptPassword(password, currentUser.getCredentials());
       
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password", encryptPassword)
                .set("salt",currentUser.getSalt())
                .set("gmt_modified", DateTimeUtil.localDateTimeToDate(LocalDateTime.now()))
                .eq("user_id",currentUser.getUserId());
    
        boolean update = userService.update(updateWrapper);
    
        return update ? Result.ok().message("重置密码成功") : Result.error().message("重置密码失败");
    }
    
    
    
    
    @PostMapping("/profile/updateAvatar")
    @ResponseBody
    public Result<Object> updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        boolean update = false;
        try {
            if (!file.isEmpty()) {
                String upload = FileUtil.upload(baseDir, file);
                
                UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("avatar_url", upload)
                        .set("gmt_modified", DateTimeUtil.localDateTimeToDate(LocalDateTime.now()))
                        .eq("user_id",ShiroUtil.getCurrentUser().getUserId());
                update = userService.update(updateWrapper);
            }
        } catch (IOException e) {
            log.error("文件上传失败:{}",e.getMessage());
            update = false;
        }
    
        return update ? Result.ok().message("头像修改成功") : Result.error().message("头像修改失败");
    }

}