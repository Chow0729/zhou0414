package com.zhou.grad.auth.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhou.grad.auth.model.LoginUserModel;
import com.zhou.grad.entity.User;
import com.zhou.grad.entity.UserCompany;

public interface UserService {

    /**
     * 根据条件分页查询所有的用户
     * @return
     */
    Map<String, Object> selectAllUsersByPage(String selectedFiled, String condition, String sortOrder, Integer isenable, int start, int pageSize);
    
    /**
     * 添加用户和用户部门信息
     * @param user
     * @param uCompany
     * @return
     */
    int addUser(User user, UserCompany uCompany);
    
    /**
     * 修改用户
     * @param user
     * @param uCompany
     * @return
     */
    Map<String, Object> editUser(User user, UserCompany uCompany);
    
    /**
     * 根据userId查询用户及其部门信息
     * @param userId
     * @return
     */
    Map<String, Object> selectUserWithDeptByUserId(int userId);
    
    /**
     * 修改用户的状态
     * @param ids 要修改的用户id
     * @param status 修改后的状态
     * @return
     */
    Map<String, Object> updateUserStatus(Integer[] ids, int status);
    
    /**
     * 批量删除用户
     * @param ids 被删除的用户的id
     * @return
     */
    Map<String, Object> deleteUsers(Integer[] ids);
    
    /**
     * @param user
     * @description 根据remember的值，添加或删除cookie
     */
    void remember(LoginUserModel user, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 登陆验证
     * @param user
     * @description 验证登录
     * @return 验证结果
     */
    String checkLogin(LoginUserModel loginUser);
    
    /**
     * 根据用户名或mail查询
     * @param condition
     * @return
     */
    User selectUserByCondition(String condition);
    
    /**
     * 查询用户的资源url
     * @param userId
     * @return
     */
    List<String> selectResourceUrlByUserId(int userId);
    
    /**
     * 查询所有的资源url
     * @return
     */
    List<String> selectAllResourceUrls();
    
    /**
     * 验证注册时的用户名或邮箱是否已存在
     * @param userName
     * @param email
     * @return
     */
    String regisetValidate(String userName, String email);
    
    /**
     * 处理用户注册事件
     * @param user
     * @param request
     * @return
     */
    String processRegister(User user, HttpServletRequest request);
    
    /**
     * 根据用户名或者邮箱查询用户的账号
     * @param account 用户名或邮箱
     * @return
     */
    String getEmailByAccount(String account);
    
    /**
     * 判断用户的状态是否启用
     * @param email
     * @return
     */
    boolean isActivated(String email);
    
    /**
     * 发送邮件
     * @param request
     * @param email 接收邮件的邮箱
     */
    void sendEmail(HttpServletRequest request, String email);
    
    /**
     * 更新用户密码
     * @param email  邮箱
     * @param rPassword  修改后的密码
     * @return
     */
    int updatePassword(String email, String rPassword);
}
