package com.hwadee.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;


public class FileUpLoad {

    /**
     *
     * @param file         单个文件
     * @param filePath     文件上传路径
     * @return             上返回文件的大小，String类型
     */
    public static String uploadFile(MultipartFile file, String filePath) {
        //   文件名字
        String fileName = file.getOriginalFilename();
        try {
            upload(file.getBytes(), filePath, fileName);
            return byteToKb(file.getSize());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 指定文件名
     * @param file
     * @param filePath
     * @param fileName
     * @return
     */
    public static String uploadFile(MultipartFile file, String filePath,String fileName) {
        try {
            upload(file.getBytes(), filePath, fileName);
            return byteToKb(file.getSize());
        } catch (Exception e) {
            return "";
        }
    }


    /**
     *
     * @param files        全部文件
     * @param filePath     文件上传路径
     * @return             返回文件的大小，String类型
     */
    public static String uploadFiles(MultipartFile[] files,String filePath) {
        //   依次上传每个文件
        long totalSize=0;
        for (MultipartFile file : files) {
            //   文件名字
            String fileName = file.getOriginalFilename();
            totalSize+=file.getSize();
            try {
                upload(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                return "";
            }
        }
        String size = byteToKb(totalSize);
        return size;
    }


    /**
     *
     * @param size    文件字节大小
     * @return        返回KB或者B格式的文件大小，字符串类型
     */
    public static String byteToKb(long size){
        DecimalFormat df = new DecimalFormat("0.00");
        if(size<=1024){
            return String.valueOf(size)+"B";
        }else if((size/1024)>=1 && (size/1024)<=1024){
            return df.format((float)size/1024)+"KB";
        }else if((size/1024)>1024 && (size/1024/1024)<=1024){
            return df.format((float)size/1024/1024)+"MB";
        }else{
            return df.format((float)size/1024/1024/1024)+"MB";
        }
    }

    /**
     *
     * @param file              需要上传的文件
     * @param filePath          上传的文件路径
     * @param fileName          上传的文件名
     * @throws Exception
     */
    public static String upload(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
        return filePath+fileName;
    }

}
