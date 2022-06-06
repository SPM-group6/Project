package com.hwadee.tools;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

public class FaceUtils {
    private FaceEngine faceEngine;
    private ImageInfo imageInfo;
    //private ImageInfo imageGrayInfo;
    private FunctionConfiguration functionConfiguration;

//    private String appId="3PHKpH8apXnsSkzWZvfcywWQ8HfozcxncyqYsmz2LZJX";
//    private String sdkKey="3Qpe6DQjJWQdKoKpqXJGkCgUJdnz8vG1D1c5mStPiH9L";
    private String appId = "Bj3d1pCxSv3BpNzvmRJJq6Dn8KeYPCczh1B1XAiNKiBA";
    private String sdkKey = "Ht7PyP8416dKwQWCohxwSeQ6nRuhpGRbzZ8TnTxtioad";

//    private static final String configFile = "/home/static/LINUX64";
//      private static final String configFile = "C:/Users/28626/Documents/PSM/WIN64";//LH
      private static final String configFile = "C:/Users/lsj/Desktop/ArcSoft_ArcFace_Java_Windows_x64_V3.0/libs/WIN64";


    public FaceUtils(){

        faceEngine = new FaceEngine(configFile);

        int errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }


        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
    }

    public FaceUtils(String imageUrl){
        imageInfo = getRGBData(new File(imageUrl));
        //imageGrayInfo = getGrayData(new File(imageUrl));
        faceEngine = new FaceEngine(configFile);

        int errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }


        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
    }

    public FaceEngine getFaceEngine() {
        return faceEngine;
    }

    public void setImageInfo(String imageUrl) {
        this.imageInfo = getRGBData(new File(imageUrl));
    }

//    public void setImageGrayInfo(String imageUrl){
//        this.imageGrayInfo = getGrayData(new File(imageUrl));
//    }

    // 比对的两张图片特征值
    public float compareTo(byte[] byte1,byte[] byte2){
        //特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(byte1);
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(byte2);
        FaceSimilar faceSimilar = new FaceSimilar();
        int errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        if (errorCode != ErrorInfo.MOK.getValue())return -1;
        System.out.println("相似度：" + faceSimilar.getScore());
        return faceSimilar.getScore();
    }

    // 获取人脸信息
    // 通告imageInfo 检测人脸信息
    public  List<FaceInfo> getFaceInfo(){
        if (imageInfo == null)return null;
        List<FaceInfo> faceInfoList = new ArrayList<>();
        int errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        if (errorCode != ErrorInfo.MOK.getValue())return null;
        if (faceInfoList.size() == 0)return null;
        return faceInfoList;
    }

    // 活体检测
    // 常规的RGB检测
    public  boolean isLive(){
        if (imageInfo == null)return false;
        //设置活体测试
        faceEngine.setLivenessParam(0.5f, 0.7f);
        FunctionConfiguration configuration = new FunctionConfiguration();
        configuration.setSupportAge(true);
        configuration.setSupportFace3dAngle(true);
        configuration.setSupportGender(true);
        configuration.setSupportLiveness(true);

        List<FaceInfo> faceInfo = getFaceInfo();
        int errorCode = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfo, configuration);
        if (errorCode != ErrorInfo.MOK.getValue())return false;

        //活体检测
        List<LivenessInfo> livenessInfoList = new ArrayList<>();
        errorCode = faceEngine.getLiveness(livenessInfoList);
        if (errorCode != ErrorInfo.MOK.getValue())return false;
        if (livenessInfoList.size() == 0)return false;

//        //IR属性处理
//        if(imageGrayInfo == null) return false;
//
//        List<FaceInfo> faceGrayInfoList = new ArrayList<FaceInfo>();
//        errorCode = faceEngine.detectFaces(imageGrayInfo.getImageData(), imageGrayInfo.getWidth(), imageGrayInfo.getHeight(), imageGrayInfo.getImageFormat(), faceGrayInfoList);
//        if(errorCode != ErrorInfo.MOK.getValue())return false;
//
//        FunctionConfiguration configurationIR = new FunctionConfiguration();
//        configurationIR.setSupportIRLiveness(true);
//        errorCode = faceEngine.processIr(imageGrayInfo.getImageData(), imageGrayInfo.getWidth(), imageGrayInfo.getHeight(), imageGrayInfo.getImageFormat(), faceGrayInfoList, configurationIR);
//        if(errorCode != ErrorInfo.MOK.getValue())return false;
//
//        // IR活体检测
//        List<IrLivenessInfo> irLivenessInfo = new ArrayList<>();
//        errorCode = faceEngine.getLivenessIr(irLivenessInfo);
//        if(errorCode != ErrorInfo.MOK.getValue())return false;

        return livenessInfoList.get(0).getLiveness() == 1; //&& irLivenessInfo.get(0).getLiveness() == 1;
    }

    // 获取人脸特征值
    public byte[] getFaceFeature(){
        if (imageInfo == null)return null;
        if (!isLive())return null;
        List<FaceInfo> faceInfoList = getFaceInfo();
        if (faceInfoList == null || faceInfoList.size() == 0)return null;
        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        int errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        if (errorCode != ErrorInfo.MOK.getValue())return null;
        return faceFeature.getFeatureData();
    }

    public void unInit(){
        faceEngine.unInit();
    }

}
