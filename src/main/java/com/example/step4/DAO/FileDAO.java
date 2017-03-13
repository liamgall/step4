package com.example.step4.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.step4.VO.FileVO;
import com.example.step4.VO.UserVO;

public interface FileDAO {

	public void insertDAO(HashMap<String, Object> map);
	public List<HashMap<String, Object>> selectAllDAO(String email);
}
