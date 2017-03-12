package com.example.step4;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.step4.DAO.UserDAO;
import com.example.step4.Util.Encrypter;
import com.example.step4.VO.UserVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String home(@ModelAttribute(value="UserVO") UserVO user) {
		Encrypter encrypter = new Encrypter();
		String encPwd = encrypter.getEncryptedPassword(user.getPassword(), user.getEmail());
		user.setPassword(encPwd);
		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		
		dao.insertDAO(user);
		return "registerDone";
	}
	
	
	@RequestMapping(value= "/selectUser", method= RequestMethod.POST)
	@ResponseBody
	public UserVO selectUser(@RequestBody String email){

		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		UserVO user = new UserVO();
		System.out.println(email);
		
		user = dao.findDAO(email);
		System.out.println(user.getEmail());
		
		return user;
	}
	
	
	@RequestMapping(value= "/changePhoneNumber", method= RequestMethod.POST)
	@ResponseBody
	public String changePhoneNumber(@RequestParam(value="phoneNumber")String phoneNumber, @RequestParam(value="email")String email){
		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNumber", phoneNumber);
		map.put("email", email);
		
		dao.changePhoneDAO(map);
		return phoneNumber;
	}
	
}
