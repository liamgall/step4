package com.example.step4.Util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class Encrypter {
	
	public String getEncryptedPassword(String password, String salt){
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(512);
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		String encodedPassword = shaPasswordEncoder.encodePassword(password, salt);
		return encodedPassword;
	}
}
