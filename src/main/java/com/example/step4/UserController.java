package com.example.step4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.step4.VO.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String home(@ModelAttribute(value="UserVO") UserVO user) {
		System.out.println(user);
		return "home";
	}
	
}
