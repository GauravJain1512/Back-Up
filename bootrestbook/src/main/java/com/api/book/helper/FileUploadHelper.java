package com.api.book.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
//	public final String Upload_dir="D:\\CDAC\\JAVA\\Spring boot By Durgesh\\CodeAndProjects\\bootrestbook\\src\\main\\resources\\static\\image";
	public final String Upload_dir=new ClassPathResource("static/image/").getFile().getAbsolutePath();
	public FileUploadHelper() throws IOException{
		
	}
	public boolean uploadFile(MultipartFile file) {
		boolean f = false;
		
		try {
			
			InputStream inputStream = file.getInputStream();
			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);
			FileOutputStream fos = new FileOutputStream(Upload_dir+File.separator+file.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();
			f = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
