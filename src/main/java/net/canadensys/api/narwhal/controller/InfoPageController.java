package net.canadensys.api.narwhal.controller;

import net.canadensys.api.narwhal.config.NarwhalConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Information pages Controller.
 * 
 * @author canadensys
 *
 */
@Controller
public class InfoPageController {
	
	@RequestMapping(value={"/api"}, method={RequestMethod.GET})
	public String handleApiPage(ModelMap model, HttpServletRequest request){
		model.addAttribute(NarwhalConfiguration.PAGE_ROOT_MODEL_KEY, ControllerHelper.createPageModel(request));
		return "api";
	}
	
	@RequestMapping(value={"/about"}, method={RequestMethod.GET})
	public String handleAboutPage(ModelMap model, HttpServletRequest request){
		model.addAttribute(NarwhalConfiguration.PAGE_ROOT_MODEL_KEY, ControllerHelper.createPageModel(request));
		return "about";
	}
}
