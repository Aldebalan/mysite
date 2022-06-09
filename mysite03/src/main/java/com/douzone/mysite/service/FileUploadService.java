package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String RESTORE_PATH = "/mysite-uploads";
	private static String URL_BASE = "/assets/gallery";

	public static String restore(MultipartFile multipartFile) {
		String url = null;

		if (multipartFile.isEmpty()) {
			return url;
		}

		try {

			File restoreDirectory = new File(RESTORE_PATH);
			if (!restoreDirectory.exists()) {
				restoreDirectory.mkdirs();
			}

			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf('.') + 1);
			String restoreFilename = generateSaveFilename(extName);
			Long fileSize = multipartFile.getSize();

			System.out.println("FileName : " + originFileName);
			System.out.println("restoreFilename : " + restoreFilename);
			System.out.println("FileSize : " + fileSize);
			
			byte[] data = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(RESTORE_PATH + "/" + restoreFilename);
			os.write(data);
			os.close();
			
			url = URL_BASE + "/" + restoreFilename;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return url;
	}

	private static String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calender = Calendar.getInstance();
		
		filename += calender.get(Calendar.YEAR);
		filename += calender.get(Calendar.MONTH);
		filename += calender.get(Calendar.DATE);
		filename += calender.get(Calendar.HOUR);
		filename += calender.get(Calendar.MINUTE);
		filename += calender.get(Calendar.SECOND);
		filename += calender.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		return filename;
	}

}
