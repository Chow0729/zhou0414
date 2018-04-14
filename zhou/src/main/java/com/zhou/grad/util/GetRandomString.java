package com.zhou.grad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetRandomString {

    public static String getRandom(int length) {
        //获取当前时间戳
          SimpleDateFormat sf = new SimpleDateFormat("MMddHH");
          String temp = sf.format(new Date());
          if (length<6) {
              return "随机数的长度必须大于6";
          }
          if (length==6) {
              return temp;
          }
          
          int num = (int) (Math.random()*9*Math.pow(10, length-7)+Math.pow(10, length-7));
          String random = String.valueOf(num);
          System.out.println(random);
          return temp + random;
      }
    public static void main(String[] args) {
        String string = GetRandomString.getRandom(10);
        System.out.println(string);
    }
}
