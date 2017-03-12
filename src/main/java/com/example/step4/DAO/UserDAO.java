package com.example.step4.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.step4.VO.UserVO;

public interface UserDAO {

	public void insertDAO(UserVO user);
	public List<HashMap<String, Object>> selectAllDAO(int start);
	public int countAllDAO(UserVO user);
	public UserVO findDAO(String email);
	public void changePhoneDAO(Map<String, String> map);
}
