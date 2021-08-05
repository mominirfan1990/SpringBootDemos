package com.jayshree.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StudentApplicationController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showStudentForm(ModelMap model) {
		
		return "getStudentInfo";
	}
	
	

	@RequestMapping(value = "/studentFormAction", method = RequestMethod.GET)
	public String getStudentData(HttpServletRequest request ,ModelMap model)
	{
		String str=request.getParameter("studName");
		model.addAttribute("greeting", "Hello ,"+str+" from Spring 4 MVC");
		return "welcome";
	}
	@RequestMapping(value="/enquiry")
	public String processing()	{
		
		
		return "next";
	}
}
