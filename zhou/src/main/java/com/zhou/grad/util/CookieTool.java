package com.zhou.grad.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 添加cookie的工具
 * @author ChaoQun Zhou
 * @date 2018年3月26日
 */
public class CookieTool {

    /**
     * 设置cookie（接口方法）
     * 
     * @author 周超群
     * @param response
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 删除cookie（接口方法）
     * 
     * @author 周超群
     * @param request
     * @param response
     * @param name
     *            cookie名字
     * @return
     */
    public static String deleteCookie(HttpServletResponse response, HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "删除成功";
        } else {
            return "cookie不存在";
        }
    }

    /**
     * 将cookie封装到Map里面（非接口方法）
     * 
     * @author 周超群
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {      
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>(cookies.length);
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
