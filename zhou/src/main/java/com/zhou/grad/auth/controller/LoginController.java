package com.zhou.grad.auth.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhou.grad.auth.model.LoginUserModel;
import com.zhou.grad.auth.service.ResourceService;
import com.zhou.grad.auth.service.UserService;
import com.zhou.grad.entity.User;
import com.zhou.grad.util.PictureVerificationCodeUtil;

@Controller
public class LoginController {

    @Autowired
    private UserService userService; 
    
    @Autowired
    private ResourceService resourceService;
    
    @ResponseBody
    @RequestMapping("login.do")
    public ModelAndView login(LoginUserModel userModel, HttpSession session) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        String error = userService.checkLogin(userModel);
        User user = userService.selectUserByCondition(userModel.getUsername());
        if (user != null) {
            List<String> resUrlList = userService.selectResourceUrlByUserId(user.getUserId());
            List<String> allUrls = userService.selectAllResourceUrls();
            session.setAttribute("allUrls", allUrls);
            session.setAttribute("purview", resUrlList);
        }
        session.setAttribute("User", user);
        session.setAttribute("username", userModel.getUsername());      
        view.getModel().put("userModel", userModel);
        view.getModel().put("username", URLEncoder.encode(userModel.getUsername(), "utf-8"));
        view.getModel().put("error", URLEncoder.encode(error, "utf-8"));
        return view;
    }
    
    /**
     * @description 登出，清除session
     * @param session
     * @return 登录界面
     * @throws Exception
     */
    @GetMapping(value = "/login_out.do")
    public String logout(HttpSession session) throws Exception{  
        //清除Session  
        session.invalidate();
        return "../loginAndRegister";  
    }
    
    /**
     * 验证注册的用户名或邮箱是否已存在
     * @return
     */
    @ResponseBody
    @RequestMapping("/registerValidate")
    public Map<String, String> registerValidate(@RequestParam String userName, @RequestParam String email) {
        String message = userService.regisetValidate(userName, email);
        Map<String, String> map = new HashMap<>(1);
        map.put("message", message);
        return map;
    }
    
    /**
     * 注册
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/register.do")
    public ModelAndView register(@ModelAttribute User user, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        System.out.println(user);
        String error = "";
        boolean isAssignRolesError = false;
        error = userService.processRegister(user, request);
        if (!"".equals(error)) {
            if ("角色分配失败，请联系管理员！".equals(error)) {
                isAssignRolesError = true;
            }           
            view.getModel().put("isAssignRolesError", isAssignRolesError);
            view.getModel().put("error", error);
            view.setViewName("../register_failure");            
        } else {
            view.setViewName("../register_success");
        }
        return view;
    }
    
    /**
     * @author ChaoQun Zhou
     * @description 生成图片验证码，写入输入流，并将验证码字符串加入session
     * @param request
     * @param response
     */
    @GetMapping(value="/verificationPicture")
    @ResponseBody
    public void verificationPicture(HttpServletRequest request, HttpServletResponse response) {
        try {
            PictureVerificationCodeUtil.generateVerificationCode(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    
    /**
     * @author ChaoQun Zhou
     * @description 跳转到身份验证界面
     * @param account
     *              账号
     * @return ModelAndView
     */
    @PostMapping(value="/identityVerification")
    public ModelAndView rpSecond(@RequestParam String account) {
        ModelAndView view = new ModelAndView();
        view.setViewName("rpSecond");
        view.getModel().put("email", userService.getEmailByAccount(account));
        view.getModel().put("account", account);
        return view;
    }
    
    /**
     * @author ChaoQun Zhou
     * @description 根据key获取session中的值，并与传入的值比对
     * @param request
     * @param checkCode
     *              用户输入的验证码
     * @param sessionName
     *              要获取的session的Key
     * @return true or false
     */
    @PostMapping(value="/validateCode")
    @ResponseBody
    public Boolean validateCode(HttpServletRequest request, @RequestParam String checkCode, @RequestParam String sessionName) {
        HttpSession session = request.getSession();
        String verificationCode = (String)session.getAttribute(sessionName);
        session.removeAttribute(sessionName);
        if (verificationCode!=null && verificationCode.toLowerCase().equals(checkCode.toLowerCase())) {
            return true;
        } else {
            return false;
        }   
    }
    
    /**
     * @author ChaoQun Zhou
     * @description 发送邮件验证码
     * @param email
     *          邮箱地址
     * @param request
     */
    @PostMapping(value="/getValidateCode")
    @ResponseBody
    public int getValidateCode(@RequestParam String email, HttpServletRequest request) {
        if (userService.isActivated(email)) {
            userService.sendEmail(request, email);
            return 1;
        } else {
            return 0;
        }   
    }
    
    /**
     * @author ChaoQun Zhou 
     * @description 保存新的密码，并跳转到结束界面
     * @param email
     *              注册邮箱
     * @param password
     *              新密码
     * @param rPassword
     *              确认新密码
     * @return
     */
    @PostMapping(value="/updatePassword")
    public ModelAndView updatePassword(@RequestParam String email, @RequestParam String password, @RequestParam String rPassword) { 
        ModelAndView view = new ModelAndView();
        int result = userService.updatePassword(email, rPassword);
        if (result == 1) {
            view.getModel().put("success", true);
        } else {
            view.getModel().put("success", false);
        }
        view.setViewName("rpFinal");
        return view;
    }
    
    /**
     * 跳转到首页
     * @return
     */
    @GetMapping(value = "/index.do")
    public String toIndex() {
        return "index";
    }
    
    /**
     * @author ChaoQun Zhou
     * @description 跳转到设置新密码界面
     * @return
     */
    @PostMapping(value="/setNewPassword")
    public String setNewPassword() {
        return "rpSetNewPassword";
    }
}
