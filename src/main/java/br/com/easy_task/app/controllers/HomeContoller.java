package br.com.easy_task.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeContoller {
	
	@RequestMapping("/")
	public ModelAndView index() {
		
		ModelAndView modelAndViewIndex = new ModelAndView("index");
		
		return modelAndViewIndex;
	}
	
	
}
