package net.canadensys.api.narwhal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Informations pages Controller.
 * 
 * @author canadensys
 *
 */
@Controller
public class InfoPageController {
	
	@RequestMapping(value={"/api"}, method={RequestMethod.GET})
	public String handleApiPage(){
		return "api";
	}
	
	@RequestMapping(value={"/about"}, method={RequestMethod.GET})
	public String handleAboutPage(){
		return "about";
	}
}
