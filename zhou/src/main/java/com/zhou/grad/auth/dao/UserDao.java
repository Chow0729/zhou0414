package com.zhou.grad.auth.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.auth.model.UserInfoModel;
import com.zhou.grad.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer userId);
    
    /**
     * 批量删除用户
     * @param ids 被删除用户的id
     * @return
     */
    int deleteBatch(Integer[] ids);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    
    /**
     * 根据条件分页查询所有的用户
     * @param map
     * @return
     */
    List<UserInfoModel> selectAllUsersByPage(Map<String, Object> map);
    
    /**
     * 根据条件统计所有的用户
     * @param map
     * @return
     */
    int countAllUsers(Map<String, Object> map);
    
    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User selectByUserName(String userName);
    
    /**
     * 根据邮箱查询用户
     * @param mail
     * @return
     */
    User selectByMail(String mail);
    
    Map<String, Object> selectUserWithDeptByUserId(int userId);
    
    /**
     * 根据用户名或mail查询
     * @param condition
     * @return
     */
    User selectUserByCondition(String condition);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 批量修改用户的状态
     * @param map 参数
     * @return
     */
    int updateUserStatus(Map<String, Object> map);
}