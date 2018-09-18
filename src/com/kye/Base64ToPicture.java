package com.kye;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.rmi.runtime.Log;

public class Base64ToPicture {
	
	private static final String PIC_TAG = "---[DEBUG]---ServiceImp---upLoadImage str1=";
	private static final String PIC_END = "; Method";
	
	//读取txt.文件
	public boolean readTxtFile(String filePatch){
		
		boolean flag = false;
		
		File txtFile = new File(filePatch);
		
		String picFile2 = filePatch.substring(0, filePatch.lastIndexOf("\\")+1);
		File dirPic = new File(picFile2,"日志图片");
		if(dirPic.exists()) {
			dirPic.delete();
		}else {
			dirPic.mkdir();
		}
		
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(txtFile));
			BufferedReader br = new BufferedReader(reader);
			
			String line = "";
			line = br.readLine();
			while(line != null) {
				if(!"".equals(line)) {
					if(line.contains(PIC_TAG) && line.contains(PIC_END)) {
						int startIndex = line.indexOf(PIC_TAG);
						int endIndex =  line.indexOf(PIC_END);
						String picName = line.substring(0,startIndex).replace(" ", "T").replace(":", "-");
						
						//截取base64；
						String base64Str = line.substring(startIndex, endIndex).replace(PIC_TAG, "");
						flag = base2Pic(base64Str, dirPic.getAbsolutePath()+"\\"+picName+".jpg");
					}
				}
				line = br.readLine();
			}
			
			reader.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	//base64转换成图片
	private  boolean base2Pic(String base64Str,String picPatch) {
		boolean flag = false;
		
		if(base64Str == null || base64Str.equals("")) {
			flag = false;
		}else {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				//base64解码
				byte[] b = decoder.decodeBuffer(base64Str);
				for(int i = 0; i < b.length; i++) {
					if(b[i] < 0) {
						b[i] += 256;
					}
				}
				
				//生成图片
				OutputStream out =  new FileOutputStream(picPatch);
				out.write(b);
				out.flush();
				out.close();
				
				flag = true;
				
			}catch(Exception e) {
				flag = false;
			}
		}
		
		return flag;
	}
	
}
