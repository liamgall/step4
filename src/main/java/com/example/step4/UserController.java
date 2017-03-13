package com.example.step4;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.step4.DAO.FileDAO;
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
	public String home(@ModelAttribute(value="UserVO") UserVO user, MultipartHttpServletRequest mhsq) throws IllegalStateException, IOException {
		Encrypter encrypter = new Encrypter();
		String encPwd = encrypter.getEncryptedPassword(user.getPassword(), user.getEmail());
		user.setPassword(encPwd);
		String root_path = mhsq.getSession().getServletContext().getRealPath("/resources/attatchments/");
		UserDAO udao = sqlSession.getMapper(UserDAO.class);
		udao.insertDAO(user);
		
		FileDAO fdao = sqlSession.getMapper(FileDAO.class);
		List<MultipartFile> mf = mhsq.getFiles("uploadFiles");
		for(int i=0 ;i< mf.size(); ++i){
			String file = mf.get(i).getOriginalFilename();
			FileTransfer ft = new FileTransfer(mf.get(i), root_path);
			ft.uploadFile();
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("fileName", file);
			hm.put("email", user.getEmail());
			fdao.insertDAO(hm);
		}
		return "registerDone";
	}
	
	
	@RequestMapping(value= "/selectUser", method= RequestMethod.POST)
	@ResponseBody
	public UserVO selectUser(@RequestBody String email){

		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		UserVO user = new UserVO();
		
		user = dao.findDAO(email);
		
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
	
	@RequestMapping(value= "/changePassword", method= RequestMethod.POST)
	@ResponseBody
	public String changePassword(@RequestParam(value="password")String password, @RequestParam(value="email")String email){
		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		System.out.println(email);
		Map<String, String> map = new HashMap<String, String>();
		Encrypter encrypter = new Encrypter();
		String encPwd = encrypter.getEncryptedPassword(password, email);
		System.out.println(encPwd);
		map.put("password", encPwd);
		map.put("email", email);
		dao.changePasswordDAO(map);
		return password;
		
	}
	
	
}
