package com.zhou.grad.company.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.entity.Department;

public interface DepartmentDao {
    int deleteByPrimaryKey(Integer depId);
    
    int deleteByIds(Integer[] deptIds);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer depId);
    
    /**
     * 查询所有的部门
     * @return
     */
    List<Department> selectAllDepts();
    
    /**
     * 分页查询所有的部门
     * @param map
     * @return
     */
    List<Department> selectAllDeptsPage(Map<String, Object> map);
    
    /**
     * 统计所有的部门
     * @return
     */
    int countAllDepts();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}