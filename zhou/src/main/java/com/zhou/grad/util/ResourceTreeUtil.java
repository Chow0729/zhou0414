package com.zhou.grad.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhou.grad.auth.model.ResourceModal;
import com.zhou.grad.entity.Resource;

public class ResourceTreeUtil {

    
    /**
     * 根据父节点的id获取所有子节点
     * @param list 用户的所有的资源
     * @param pid 传入的父节点id
     * @return 带有子节点的资源列表
     */
    public static List<ResourceModal> getChildPerms(List<ResourceModal> list, int pid) {
        List<ResourceModal> returnList = new ArrayList<ResourceModal>();
        for (ResourceModal r : list) {
            if (r.getPid() == pid) {
                getChildren(list, r);
                returnList.add(r);
            }
        }
        return returnList;
    }
    
    private static void getChildren(List<ResourceModal> list, ResourceModal r){
        //获取r的子资源列表
        List<ResourceModal> childList = new ArrayList<ResourceModal>();
        for (ResourceModal resourceModal : list) {
            //把资源列表中pid是资源r的id的所有资源赋值给r的children属性
            if (resourceModal.getPid() == r.getResourceId()) {
                childList.add(resourceModal);
            }
        }
        r.setChildren(childList);
    }
}
