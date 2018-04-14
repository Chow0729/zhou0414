package com.zhou.grad.company.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.company.dao.DepartmentDao;
import com.zhou.grad.company.service.DeptService;
import com.zhou.grad.entity.Department;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DepartmentDao deptDao;

    @Override
    public List<Department> selectAllDepts() {
        
        return deptDao.selectAllDepts();
    }

    @Override
    public Map<String, Object> selectAllDeptsPage(int offset, int limit) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("offset", offset);
        paramsMap.put("limit", limit);
        List<Department> list = deptDao.selectAllDeptsPage(paramsMap);
        int total = deptDao.countAllDepts();
        returnMap.put("rows", list);
        returnMap.put("total", total);
        return returnMap;
    }

    @Override
    public int insertDept(Department dept) {
        dept.setCreatedTime(new Date());
        int result = deptDao.insert(dept);
        return result;
    }

    @Override
    public int updateDept(Department dept) {
        dept.setEditTime(new Date());
        int result = deptDao.updateByPrimaryKeySelective(dept);
        return result;
    }

    @Override
    public int delDept(Integer[] deptIds) {
        int result = deptDao.deleteByIds(deptIds);
        return result;
    }
}
