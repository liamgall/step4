package com.example.step4;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileTransfer {
	private File saveFile;
	private MultipartFile file;
	private String root_path;
	
	public FileTransfer(MultipartFile file, String root_path) {
		this.file = file;
		this.root_path = root_path;
	}
	
	public void uploadFile() throws IllegalStateException, IOException{
		saveFile = new File(root_path+'/'+file.getOriginalFilename());
		
		file.transferTo(saveFile);
	}
}
