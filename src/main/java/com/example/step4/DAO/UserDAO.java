package com.example.step4.DAO;

import java.util.HashMap;
import java.util.List;

import com.example.step4.VO.UserVO;

public interface UserDAO {

	public void insertDAO(UserVO user);
	public List<HashMap<String, Object>> selectAllDAO(UserVO user);
	public int countAllDAO(UserVO user);
	public UserVO findDAO(String email);
}
