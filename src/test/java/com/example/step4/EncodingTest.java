package com.example.step4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class EncodingTest {

	@Test
	public void test() {
		String salt = "liamgall@naver.com";
		String password = "nbp123!";
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(512);
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		String encodedPassword = shaPasswordEncoder.encodePassword(password, salt);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		

		System.out.println(bCryptPasswordEncoder.encode(password));
		System.out.println(bCryptPasswordEncoder.encode(password));
		System.out.println(bCryptPasswordEncoder.encode(password));
		System.out.println(bCryptPasswordEncoder.encode(password));
		System.out.println(shaPasswordEncoder.encodePassword(password, salt));
		System.out.println(shaPasswordEncoder.encodePassword(password, salt));
		System.out.println(shaPasswordEncoder.encodePassword(password, salt));
		System.out.println(shaPasswordEncoder.encodePassword(password, salt));
	}
}

