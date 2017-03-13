package com.example.step4.VO;

import javax.validation.constraints.NotNull;

public class FileVO {
	
	@NotNull
	private String fileName;

	@NotNull
	private String email;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}