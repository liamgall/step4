package com.example.step4.VO;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class UserVO {
	@NotEmpty
	@Size(min=10, max=254)
    private String email;

	@NotEmpty
	@Size(min=13, max=13)
	private String phoneNumber;
	
	@NotEmpty
	@Size(min=2, max=4)
	private String name;
	
	@NotEmpty
	@Size(max=255)
    private String password;
	
	@NotEmpty
	private String postcode5;
	
	@NotEmpty
	@Size(min=5, max=20)
	private String address;
	
	@NotEmpty
	@Size(min=5, max=20)
	private String details;
	
	@NotEmpty
	private String extra_info;

	private MultipartFile uploadFiles;
	
	private String filePath;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPostcode5() {
		return postcode5;
	}

	public void setPostcode5(String postcode5) {
		this.postcode5 = postcode5;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getExtra_info() {
		return extra_info;
	}

	public void setExtra_info(String extra_info) {
		this.extra_info = extra_info;
	}

	public MultipartFile getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(MultipartFile uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
}