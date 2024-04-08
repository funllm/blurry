package com.example.clarity.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    /**
     * 图片是否模糊检测
     * @param file
     * @return
     */
    public String checkImageBlur(@RequestParam("file") MultipartFile file);
}
