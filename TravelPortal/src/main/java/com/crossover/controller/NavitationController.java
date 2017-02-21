package com.crossover.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavitationController {
	
	@RequestMapping(value="/")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/admin")
	public String admin(){
		return "index_admin";
	}
}
