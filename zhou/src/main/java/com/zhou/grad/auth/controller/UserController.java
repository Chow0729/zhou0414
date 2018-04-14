package com.zhou.grad.auth.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhou.grad.auth.model.ResourceModal;
import com.zhou.grad.auth.service.ResourceService;
import com.zhou.grad.auth.service.UserService;
import com.zhou.grad.company.service.DeptService;
import com.zhou.grad.entity.Department;
import com.zhou.grad.entity.User;
import com.zhou.grad.entity.UserCompany;
import com.zhou.grad.util.UploadImageUtil;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @javax.annotation.Resource(name = "configProperties")
    private Properties properties;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DeptService deptService;
    
    @Autowired
    private ResourceService resourceService;
    

    @ResponseBody
    @RequestMapping("getAllUsersByPage")
    public Map<String, Object> getAllUsersByPage(String selectedFiled, String sortOrder,String condition, Integer isenable, int start,
            int pageSize) {
        Map<String, Object> map = userService.selectAllUsersByPage(selectedFiled, condition, sortOrder, isenable, start, pageSize);
        return map;
    }
    
    @ResponseBody
    @RequestMapping("addUser")
    public Map<String, Object> addUser(User user, UserCompany uCompany, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result=0;
        if (user != null) {
            if (user.getCreatedTime() == null) {
                user.setCreatedTime(new Date());
            }
            if (user != null) {
                result = userService.addUser(user, uCompany);
            }
            map.put("data", result);
            if (result == -1) {
                map.put("message", "用户名或邮箱已存在，添加失败");
            } else if (result == 0) {
                map.put("message", "添加失败");
            } else {
                map.put("message", "添加成功");
            }
        }
        return map;
    }
    
    @ResponseBody
    @RequestMapping("editUser")
    public Map<String, Object> editUser(User user, UserCompany uCompany, HttpServletRequest request) {
        //修改信息
        Map<String, Object> returnMap = userService.editUser(user, uCompany);
        return returnMap;
    }
    
    @ResponseBody
    @RequestMapping("editUsersStatus")
    public Map<String, Object> editUsersStatus(Integer[] ids, int status) {
        //修改信息
        Map<String, Object> returnMap = userService.updateUserStatus(ids, status);
        return returnMap;
    }
    
    
    @ResponseBody
    @RequestMapping("delUsers")
    public Map<String, Object> delUsers(Integer[] ids) {
        Map<String, Object> returnMap = userService.deleteUsers(ids);
        return returnMap;
    }
    
    /**
     * 根据userId查询用户及其部门信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("getUserWithDeptByUserId")
    Map<String, Object> getUserWithDeptByUserId(int userId) {
        Map<String, Object> map = userService.selectUserWithDeptByUserId(userId);
        return map;
    }
    
    @RequestMapping("toUserManage")
    public String toUserManage(HttpServletRequest request) {
        List<Department> depts = deptService.selectAllDepts();
        request.setAttribute("departments", depts);
        return "auth/user_manage";
    }
    
    @RequestMapping("picTest")
    public String toPicTest() {
        return "auth/picTest";
    }
    
    /**
     * 返回主页面
     * @param request
     * @return
     */
    @GetMapping(value = "main")
    public String showMain(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //获取session中的用户
        User user = (User) session.getAttribute("User");
        //获取存储图片的虚拟路径
        String virtualUrl = properties.getProperty("virtual_url");
        
        //获取当前用户的所有资源
        List<ResourceModal> menus = resourceService.selectResourceByUserId(user.getUserId());
        request.setAttribute("user", user);
        request.setAttribute("virtualUrl", virtualUrl);
        request.setAttribute("menus", menus);
        return "main";
    }
    
    /**
     * @return
     * @description 显示登录界面
     */
    @GetMapping("/loginFailed")
    public String showLogin() {
        return "../loginAndRegister";
    }
    
    /**
     * 上传文件的方法
     * @param request
     * @param files
     * @param user
     */
    private void setFile(HttpServletRequest request, MultipartFile file, User user) {
        System.out.println("'"+file.getOriginalFilename()+"'");
        if (user != null && file != null) {
            try {
                // 上传图片   pic是图片的路径
                String pic = UploadImageUtil.uploadImg(file, "");
                user.setPicUrl(pic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
