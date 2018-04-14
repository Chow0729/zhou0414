package com.zhou.grad.wechat.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("wechat")
public class UploadAndDownload {

    @RequestMapping("upload")
    public void upload(MultipartFile photo) {
        System.out.println(photo.getOriginalFilename());
        String savePath = "F:\\grad_workspace\\wechat\\upload\\" + photo.getOriginalFilename();
        System.out.println(savePath);
        File file = new File(savePath);
        try {
            photo.transferTo(file);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @RequestMapping("download")
    public void download() {
        
    }
}
