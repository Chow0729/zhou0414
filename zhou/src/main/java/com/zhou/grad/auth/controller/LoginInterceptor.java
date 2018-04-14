package com.zhou.grad.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhou.grad.auth.model.LoginUserModel;
import com.zhou.grad.auth.service.UserService;
import com.zhou.grad.entity.User;

public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private UserService userService;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView view)
            throws Exception {
        String url = request.getRequestURI();
        //如果请求地址为登录，判断登录条件，成功返回主界面，失败返回登录界面
        if(url.indexOf("/login.do")>=0){  
            userService.remember((LoginUserModel) view.getModel().get("userModel"), request, response);
            if (!view.getModel().get("error").equals("")) {
                HttpSession session = request.getSession(); 
                session.invalidate();
                System.out.println(view.getModel().get("userModel"));
                view.setViewName("redirect:user/loginFailed");
            }else {
                view.clear();
                view.setViewName("redirect:user/main");
            }
        }  
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URL  
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        String url = request.getRequestURI();  
        if (!"".equals(path)) {
            url = url.substring(path.length(), request.getRequestURI().length());       
        }
        if(url.indexOf("/login")>=0 || url.indexOf("/wechat")>=0){  
            return true;  
        }  
        //获取Session  
        HttpSession session = request.getSession();  
        User user = (User) session.getAttribute("User");  
        if(user != null){ 
            @SuppressWarnings("unchecked")
            List<String> allUrls = (List<String>) session.getAttribute("allUrls");
            if (allUrls.contains("." + url)) {
                @SuppressWarnings("unchecked")
                List<String> resUrlList = (List<String>) session.getAttribute("purview");
                if (resUrlList.contains("." + url)) {
                    return true;
                } else {
                     response.sendRedirect(basePath + "loginError.jsp");
                     return false; 
                }
            }
            return true;           
        }  
        
        //不符合条件的，跳转到登录界面  
        response.sendRedirect(basePath + "loginAndRegister.jsp"); 
        return false;  
    }
}
