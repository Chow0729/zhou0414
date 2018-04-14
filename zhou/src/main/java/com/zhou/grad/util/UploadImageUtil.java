package com.zhou.grad.util;

import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.zhou.grad.entity.Food;

@Controller
public class UploadImageUtil {
    private static Properties properties;
    
   

    @Resource(name = "configProperties")
    public  void setProperties(Properties properties) {
        UploadImageUtil.properties = properties;
    }

    /**
     * 
     * @param photo 需要保存的文件
     * @param myPath 自己定义的路径
     * @return 保存在数据库里面的图片的路径(自定义路径+图片的名称)
     */
    public static String uploadImg(MultipartFile photo, String myPath) {

        if (photo.getSize() != 0) {
            // 原始名称
            String originalname = photo.getOriginalFilename();
            // 上传图片
            if (photo != null && originalname != null && originalname.length() > 0) {
                try {
                    //读取保存路径(物理路径)
                    String savePath = readImgUrl();
                    //在物理路径后加上自定义的路径
                    savePath += myPath;
                    // 判断文件路径是否存在
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    // 新的图片名称
                    String newFileName = UUID.randomUUID() + originalname.substring(originalname.lastIndexOf("."));
                    // 新的图片
                    File newFile = new File(savePath + "/" + newFileName);
                    // 将文件写入磁盘
                    photo.transferTo(newFile);

                    return myPath + "/" + newFileName;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return "";
    }

    /**
     * 删除菜单的图片
     * @param myPath 自定义的路径
     * @return
     */
    public static Boolean deleteImg(Food food) {
        //获取图片的存储路径
        String savePath = UploadImageUtil.readImgUrl();
        savePath += food.getImageUrl();
        File file = new File(savePath);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + savePath + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + savePath + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + savePath + "不存在！");
            return false;
        }
        
    }
    
    /**
     * 读取上传图片路径得配置文件
     * @return
     */
    private static String readImgUrl() {
        String picUrl=properties.getProperty("pic_url");
        return picUrl;

    }
   
}
