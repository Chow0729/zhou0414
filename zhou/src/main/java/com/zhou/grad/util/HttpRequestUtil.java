package com.zhou.grad.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import net.sf.json.JSONObject;

public class HttpRequestUtil {

    /**
     * 发送http请求
     * @return
     */
  //处理http请求  requestUrl为请求地址  requestMethod请求方式，值为"GET"或"POST"    outputStr为请求参数
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){  
        StringBuffer buffer=null;  
        try{  
        URL url=new URL(requestUrl);  
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();  
        conn.setDoOutput(true);  
        conn.setDoInput(true);  
        conn.setRequestMethod(requestMethod);  
        conn.connect();  
        //往服务器端写内容 也就是发起http请求需要带的参数  
        if(null!=outputStr){  
            OutputStream os=conn.getOutputStream();  
            os.write(outputStr.getBytes("utf-8"));  
            os.close();  
        }  
          
        //读取服务器端返回的内容  
        InputStream is=conn.getInputStream();  
        InputStreamReader isr=new InputStreamReader(is,"utf-8");  
        BufferedReader br=new BufferedReader(isr);  
        buffer=new StringBuffer();  
        String line=null;  
        while((line=br.readLine())!=null){  
            buffer.append(line);  
        }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return buffer.toString();  
    } 
    
    /**
     * 发送https请求共用体
     * method 请求方式
     * url 请求地址
     * parame 单个的参数
     * pmap 多个的参数
     * 
     */
    public static JSONObject httpsRequest(String method, String url, String parame, Map<String, Object> pmap)
            throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        // 请求结果
        JSONObject json = new JSONObject();
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl;
        HttpsURLConnection conn;
        // 查询地址
        String queryString = url;
        // 请求参数获取
        String postpar = "";
        // 字符串请求参数
        if (parame != null) {
            postpar = parame;
        }
        // map格式的请求参数
        if (pmap != null) {
            StringBuffer mstr = new StringBuffer();
            for (String str : pmap.keySet()) {
                String val = (String) pmap.get(str);
                try {
                    val = URLEncoder.encode(val, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mstr.append(str + "=" + val + "&");
            }
            // 最终参数 去掉最后一个&
            postpar = mstr.toString();
            int lasts = postpar.lastIndexOf("&");
            postpar = postpar.substring(0, lasts);
        }
        if (method.toUpperCase().equals("GET")) {
            queryString += "?" + postpar;
        }
        SSLSocketFactory ssf = BZX509TrustManager.getSSFactory();
        try {
            realUrl = new URL(queryString);
            //创立连接
            conn = (HttpsURLConnection) realUrl.openConnection();
            conn.setSSLSocketFactory(ssf);
            //设置请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); 
            if (method.toUpperCase().equals("POST")) {
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                out = new PrintWriter(conn.getOutputStream());
                out.print(postpar);
                out.flush();
            } else {
                //get请求时
                conn.connect();
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            json = JSONObject.fromObject(result);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return json;
    }
    
    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx53411c87e3fcf3b1&secret=0cb13b47747e722c0fe575c6a4e6a77a&js_code=003oMUZj2x4zYE0zVT0k2b5UZj2oMUZV&grant_type=authorization_code";
        //String url = "https://www.baidu.com";
        JSONObject json = null;
        try {
            json = HttpRequestUtil.httpsRequest("get", url, null, null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(json);  
    }
}
