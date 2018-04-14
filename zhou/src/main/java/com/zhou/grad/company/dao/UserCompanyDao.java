package com.zhou.grad.company.dao;

import com.zhou.grad.entity.UserCompany;

public interface UserCompanyDao {
    int deleteByPrimaryKey(Integer ucId);
    
    /**
     * 根据userId批量删除
     * @param userIds
     * @return
     */
    int deleteBatchByUserIds(Integer[] userIds);

    int insert(UserCompany record);

    int insertSelective(UserCompany record);

    UserCompany selectByPrimaryKey(Integer ucId);
    
    Integer selectMaxId();

    int updateByPrimaryKeySelective(UserCompany record);

    int updateByPrimaryKey(UserCompany record);
}