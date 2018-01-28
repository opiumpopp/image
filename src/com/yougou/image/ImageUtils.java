package com.yougou.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 按款色编码复制和重命名图片
 * @author deng.yq.sz
 *
 */
public class ImageUtils {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("E:\\数字化\\图片上传明细.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet hssf = wb.getSheetAt(0);
		int num = hssf.getLastRowNum();
		HSSFRow row1 = hssf.getRow(1);
		HSSFCell cell1 = row1.getCell(5);
		String s = cell1.toString();
		int index = s.lastIndexOf("_");
		String dirName = s.substring(0, index);
		copyImage(dirName);
		String[] arr = new String[num];
		for (int i = 1; i <= num; i++) {
			HSSFRow row2 = hssf.getRow(i);
			HSSFCell cell2 = row2.getCell(5);
			arr[i-1] = cell2.toString();
			copyImage(dirName, arr[i - 1]);
		}
		System.out.println("图片复制并重命名完成");
	}

	/**
	 * 拿到电脑自带的系统图片:Chrysanthemum.jpg
	 */
	public static File getImage() {
		File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures");
		//files.length=8,files[2]=desktop.ini,排除。
		File[] files = file.listFiles();
		return files[1];
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
		FileOutputStream fos = new FileOutputStream(dir + "\\" + newName + ".jpg");
 
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = fis.read(buf)) != -1) {
		    fos.write(buf, 0, len);
		}
		fis.close();
		fos.close();
	}
}
