package com.zhou.grad.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller
public class GetRequestUrlUtil {

    private static Properties properties;
    
    @Resource(name = "configProperties")
    public  void setProperties(Properties properties) {
        GetRequestUrlUtil.properties = properties;
    }
    
    public static String getRequestUrl(HttpServletRequest request) {
        //System.out.println(InetAddress.getLocalHost().getHostAddress() + "   ip");  //ip 10.103.52.44
        //System.out.println(InetAddress.getLocalHost().getHostName());  //计算机名 DESKTOP-E5RSO9A
        //System.out.println(request.getServerName()+ "  servername");  //服务器名 localhost
        //System.out.println(request.getLocalPort() + " port");  //当前端口 8088
        //获取本地的地址
        StringBuffer url = new StringBuffer("http://");
        //添加ip
        try {
            url.append(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        url.append(":");
        url.append(request.getLocalPort());
        url.append(properties.getProperty("virtual_url"));
        return url.toString();
    }
}
