package com.zhou.grad.wechat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.util.GetRequestUrlUtil;
import com.zhou.grad.util.HttpRequestUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("wechat")
public class UserInfoController {

    @ResponseBody
    @RequestMapping("test.do")
    public void test(String data) {
        System.out.println("cskdjckjsdh");
        System.out.println(data);
    }
    
    @ResponseBody
    @RequestMapping("getUserInfo")
    public JSONObject getUserInfo(String appid, String secret, String js_code, String grant_type, HttpServletRequest request) {
        JSONObject jsonObject = null;
        Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("appid", appid);
        pmap.put("secret", secret);
        pmap.put("js_code", js_code);
        pmap.put("grant_type", grant_type);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        try {
            jsonObject = HttpRequestUtil.httpsRequest("get", url, null, pmap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //添加请求地址
        jsonObject.put("requestUrl", GetRequestUrlUtil.getRequestUrl(request));
        return jsonObject;
    }
}
