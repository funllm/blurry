package com.example.clarity.service.impl;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.example.clarity.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    static {
        System.out.printf("java.library.path: %s%n",
                System.getProperty("java.library.path"));
        // 加载OpenCV库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Override
    public String checkImageBlur(MultipartFile file) {
        try {
            // 将上传的MultipartFile转换为OpenCV的Mat对象
            byte[] bytes = file.getBytes();
            Mat image = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);

            // 转换为灰度图像
            Mat grayImage = new Mat();
            Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

            // 计算图像的梯度值
            Mat laplacianImage = new Mat();
            Imgproc.Laplacian(grayImage, laplacianImage, CvType.CV_64F);

            // 计算图像的标准差
            MatOfDouble mean = new MatOfDouble();
            MatOfDouble stddev = new MatOfDouble();
            Core.meanStdDev(laplacianImage, mean, stddev);

            double[] stddevArray = stddev.toArray();
            double focusMeasure = stddevArray[0] * stddevArray[0];

            // 定义一个阈值判断图像是否模糊
            double threshold = 100;
            if (focusMeasure < threshold) {
                return "Image is blurry";
            } else {
                return "Image is not blurry";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while processing the image";
        }
    }
}
