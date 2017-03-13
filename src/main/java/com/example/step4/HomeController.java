package com.example.step4;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.step4.DAO.FileDAO;
import com.example.step4.DAO.UserDAO;
import com.example.step4.VO.UserVO;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private SqlSession sqlSession;
	
	/* 메인화면 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("userCount", dao.countAllDAO());
		return mav;
	}
	
	/* 회원가입 화면 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(){
		
		ModelAndView mav = new ModelAndView("register");
		UserVO user = new UserVO();
		mav.addObject("userVO", user);
		return mav;
	}
	
	/* 가입자 리스트 10개 단위로 불러오기 */
	@RequestMapping(value="/getList", params={"start"}, method = RequestMethod.GET)
	@ResponseBody
	public JSONPObject jsonp(@RequestParam("callback")String callback, @RequestParam(value="start")int start){
		
		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("data", dao.selectAllDAO(start));
		return new JSONPObject(callback, map);
	}
	/* 파일 테이블에서 이미지 불러오기 */
	@RequestMapping(value="/getImages", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getImages(@RequestBody String email){
		FileDAO fdao = sqlSession.getMapper(FileDAO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", fdao.selectAllDAO(email));
		return map;
	}
}
