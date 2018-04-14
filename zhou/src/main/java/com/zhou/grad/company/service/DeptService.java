package com.zhou.grad.company.service;

import java.util.List;
import java.util.Map;

import com.zhou.grad.entity.Department;

public interface DeptService {

    /**
     * 查询所有部门
     * @return
     */
    List<Department> selectAllDepts();
    
    /**
     * 分页查询所有的部门
     * @param offset
     * @param limit
     * @return
     */
    Map<String, Object> selectAllDeptsPage(int offset, int limit);
    
    /**
     * 添加部门
     * @param dept
     * @return
     */
    int insertDept(Department dept);
    
    /**
     * 修改部门
     * @param dept
     * @return
     */
    int updateDept(Department dept);
    
    /**
     * 删除部门
     * @param deptId
     * @return
     */
    int delDept(Integer[] deptIds);
}
