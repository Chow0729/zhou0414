package com.zhou.grad.company.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.company.service.DeptService;
import com.zhou.grad.entity.Department;


@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    
    /**
     * 分页查询所有部门
     * @param limit
     * @param offset
     * @return
     */
    @ResponseBody
    @RequestMapping("getAllDeptsPage")
    public Map<String, Object> getAllDeptsPage(int limit, int offset) {
        return deptService.selectAllDeptsPage(offset, limit);
    }
    
    /**
     * 添加部门
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping("addDept")
    public int addDept(Department dept) {
        System.out.println(dept);
        return deptService.insertDept(dept);
    }
    
    /**
     * 修改部门
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping("editDept")
    public int editDept(Department dept) {
        System.out.println(dept);
        return deptService.updateDept(dept);
    }
    
    /**
     * 删除部门
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping("delDept")
    public int delDept(Integer[] deptIds) {
        return deptService.delDept(deptIds);
    }
    
    /**
     * 跳转到部门管理页面
     * @return
     */
    @RequestMapping("toDeptManage")
    public String toDeptManage() {
        return "company/dept_manage";
    }
}
