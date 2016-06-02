package com.health.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	//存放头像的路径
	public static String BASE_PATH_HEAD = "e:/HealthGuarder/photos/head/";
	public static final int HEAD = 0;
	//存放门诊病历的路径
	public static String BASE_PATH_CLINIC = "e:/HealthGuarder/photos/clinic/";
	public static final int CLINIC = 1;
	//存放住院病历的路径
	public static String BASE_PATH_HOSPITAL = "e:/HealthGuarder/photos/hospital/";
	public static final int HOSPITAL = 2;
	//存放体检报告的路径
	public static String BASE_PATH_PHYSICAL = "e:/HealthGuarder/photos/physical/";
	public static final int PHYSICAL = 3;
	//图片都是以PNG的格式存储的
	public static String savePicture(String picName,byte[] bytes,int type){
		String path = null;
		String returnPath = null;
		if(type == HEAD){
			path = BASE_PATH_HEAD;
		}else if(type == CLINIC){
			path = BASE_PATH_CLINIC;
		}else if(type == HOSPITAL){
			path = BASE_PATH_HOSPITAL;
		}else if(type == PHYSICAL){
			path = BASE_PATH_PHYSICAL;
		}
		if(!createDir(path)){
			return null;
		}
		File file = new File(path+picName+".png");
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(bytes, 0, bytes.length);
			returnPath = file.getAbsolutePath();
			System.out.println(returnPath);
			out.flush();
			out.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return returnPath;
	}
	//不能直接创建文件，需先创建目录，然后创建文件java.io.WinNTFileSystem.createFileExclusively
	public static boolean createDir(String path){
		File file = new File(path);
		if(!file.exists()){
			return file.mkdirs();
		}
		return true;
	}
}
