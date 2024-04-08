# blurry
springboot + opencv 实现检测图片是否模糊、清晰度检测



## 第一步：引入opencv依赖

```xml
<dependency>
    <groupId>org.openpnp</groupId>
    <artifactId>opencv</artifactId>
    <version>4.5.3-1</version>
</dependency>
```

## 异常提示处理

**如果运行提示下面信息，则继续完成第二步。**

```java
Instantiation of bean failed; nested exception is java.lang.UnsatisfiedLinkError: no opencv_java453 in java.library.path:
```



## 第二步：引入opencv依赖库文件

将项目oepncvlib中opencv_java453.dll或libopencv_java453.so放入jdk\bin目录下

Windows对应opencv_java453.dll

Linux对应libopencv_java453.so



## 注意

对模糊度、清晰度的判断值可自行调整

```java
// 定义一个阈值判断图像是否模糊
double threshold = 100;
```

