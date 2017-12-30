package com.yougou.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * 按款色编码复制和重命名图片
 * @author deng.yq.sz
 *
 */
public class ImageUtils {
	public static void main(String[] args) throws Exception {
		//在控制台输入条码，得到条码
		Scanner in = new Scanner(System.in);
		System.out.println("请输入条码：");
		String insideCode = in.next();
		String dirName = insideCode.substring(0, insideCode.length() - 3);
		
		String image = getImageName(dirName);
		
		//根据图片得到文件后缀名
		File file = new File("E:\\image\\" + dirName + "\\" + image);
		String oldName = file.getName();
		int index = oldName.indexOf(".");
		String suffix = oldName.substring(index);
		
		//循环复制图片到指定文件夹并重新命名
		for (int i = 0; i < 9; i++) {
			String newName = insideCode + "_" + i + suffix;
			copyImage(dirName, newName);
		}
		
		for (int i = 21; i < 23; i++) {
			String newName = insideCode + "_" + i + suffix;
			copyImage(dirName, newName);
		}
		
		for (int i = 31; i < 34; i++) {
			String newName = insideCode + "_" + i + suffix;
			copyImage(dirName, newName);
		}
		
		String newName = insideCode + "_" + 41 + suffix;
		copyImage(dirName, newName);
		System.out.println("图片复制并且重命名完成");
	}

	/**
	 * 拿到电脑自带的系统图片:Chrysanthemum.jpg
	 */
	public static File getImage() {
		File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures");
		File[] files = file.listFiles();
		return files[0];
	}
	
	/**
	 * 把Chrysanthemum.jpg复制到指定目录下
	 * @throws Exception 
	 */
	public static void copyImage(String dirName) throws Exception {
		File file = getImage();
		String imageName = file.getName();
		File dir = new File("E:\\image\\" + dirName);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(dir + "\\" + imageName);
		byte[] arr = new byte[1024*8];
		int len;
		while((len = fis.read(arr)) != -1) {
			fos.write(arr, 0, len);
		}
		fis.close();
		fos.close();
		System.out.println("图片已经复制到指定目录了");
	}
	
	/**
	 * 在指定目录下，拿到图片名
	 * @param styleColorCode
	 * @return
	 */
	public static String getImageName(String dirName) {
		File file = new File("E:\\image\\" + dirName);
		String[] list = file.list();
		return list[0];
	}

	/**
	 * 复制图片的方法
	 * @param styleColorCode
	 * @param newName
	 * @throws Exception
	 */
	public static void copyImage(String dirName, String newName)
			throws Exception {
		String image = getImageName(dirName);
		File dir = new File("E:\\image\\" + dirName + "\\拍摄图\\");
		if(!dir.exists()) {
			dir.mkdir();
		}
		FileInputStream fis = new FileInputStream("E:\\image\\" + dirName + "\\" + image);
		FileOutputStream fos = new FileOutputStream(dir + "\\" + newName);
 
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = fis.read(buf)) != -1) {
		    fos.write(buf, 0, len);
		}
		fis.close();
		fos.close();
	}
}
