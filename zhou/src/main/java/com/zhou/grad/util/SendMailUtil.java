package com.zhou.grad.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
import java.util.Random;

/**
 * 发送邮件
 */
public class SendMailUtil {

    private final static  String SERVICE_HOST = "smtp.qq.com";//QQ服务器

    private final static  int    PORT = 465; //smtp的端口号

    private final static  String PROTOCOL = "smtp"; //协议名称。smtp表示简单邮件传输协议

    private final static  String ACCOUNT = "2909109450@qq.com"; //发送邮件的QQ账号

    private final static  String AUTH_CODE = "xxlikgyvrufsdcdj"; //QQ授权码(需要到https://mail.qq.com/申请)

     

    private static final JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

    /*

     *发送QQ邮件的初始化配置

     */

    static{

    senderImpl.setHost(SERVICE_HOST); //设置 使用QQ邮箱发送邮件的主机名

    senderImpl.setPort(PORT); //设置端口号

    senderImpl.setProtocol(PROTOCOL); //协议名称

    senderImpl.setUsername(ACCOUNT); // 设置自己的邮箱帐号名称

    senderImpl.setPassword(AUTH_CODE); // 设置对应账号申请到的授权码

    Properties prop = new Properties();  

    prop.put(" mail.smtp.auth ", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  

    prop.put("mail.smtp.starttls.enable", "true");

    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //使用ssl协议来保证连接安全

    prop.put(" mail.smtp.timeout ", "25000"); //传输超时时间

    senderImpl.setJavaMailProperties(prop);

    }

    /**

     * 发送简单邮件

     * @param accounts  被发邮件的用户数组

     * @param info      邮件信息

     * @param title     邮件主题

     */

    public static void sendSimpleMail(String[] accounts,String info,String title){

    //创建简单邮件对象

    SimpleMailMessage mailMessage = new SimpleMailMessage();

    mailMessage.setTo(accounts);  //设置邮件接收者账号数组

    mailMessage.setFrom(ACCOUNT); //设置邮件的发送者

    mailMessage.setSubject(title);//设置邮件的主题

    mailMessage.setText(info);    //设置邮件的内容

    //发送邮件
    senderImpl.send(mailMessage);

    }

    public static void main(String args[])  {

    String[] accounts = {"18257130417@139.com"};
    /*//发送邮件,args[1] 邮件主题，args[2] 邮件内容

    try{
        sendSimpleMail(accounts, "ceshi", "ceshi");

    } catch (Exception e) {

    e.printStackTrace();

    }*/
        char[] ch = "0123456789".toCharArray();
        Random r = new Random(); 
        int index;
        StringBuffer info = new StringBuffer("您正在修改密码，验证码是：");
        StringBuffer checkCode = new StringBuffer();
        for(int i=0; i<4; i++){ 
            index = r.nextInt(ch.length);
            checkCode.append(ch[index]);
        }
        info.append(checkCode);
        info.append("\n如非本人操作，请联系管理员！");
        String title = "您正在修改密码";
        SendMailUtil.sendSimpleMail(accounts, info.toString(), title);

           System.out.println(" 邮件发送成功.. ");  

    }  

}