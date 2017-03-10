package com.example.step4;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		return "JoinSuccess";
	}
	
}
