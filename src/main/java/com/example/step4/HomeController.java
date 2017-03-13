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

	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {

		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		UserVO user = new UserVO();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("userCount", dao.countAllDAO(user));
		return mav;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(){
		
		ModelAndView mav = new ModelAndView();
		UserVO user = new UserVO();
		mav.setViewName("register");
		mav.addObject("userVO", user);
		return mav;
	}
	
	@RequestMapping(value="/getList", params={"start"}, method = RequestMethod.GET)
	@ResponseBody
	public JSONPObject jsonp(@RequestParam("callback")String callback, @RequestParam(value="start")int start){
		
		UserDAO dao = sqlSession.getMapper(UserDAO.class);
		dao = sqlSession.getMapper(UserDAO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("data", dao.selectAllDAO(start));
		return new JSONPObject(callback, map);
	}
	
	@RequestMapping(value="/getImages", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getImages(@RequestBody String email){
		FileDAO fdao = sqlSession.getMapper(FileDAO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", fdao.selectAllDAO(email));
		return map;
	}
}
