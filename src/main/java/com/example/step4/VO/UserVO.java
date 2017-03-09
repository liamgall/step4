package com.example.step4.VO;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class UserVO {
	@NotEmpty
    private String email;

	@NotEmpty
	@Size(min=13, max=14)
	private String phoneNumber;
	
	@NotEmpty
	@Size(min=8, max=16)
    private String password;
	
	@NotEmpty
	private String postcode5;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String details;
	
	@NotEmpty
	private String extra_info;
	
	@NotEmpty
	private String captcha;

	private MultipartFile uploadFile;
	
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

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

 
}