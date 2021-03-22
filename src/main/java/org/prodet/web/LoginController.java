package org.prodet.web;

import org.prodet.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	private TypeService typeService;

	// Login form
	@RequestMapping(value = "/login.html", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("types", typeService.getAllType());
		return "login.html";
	}

	// Login form with error
	@RequestMapping(value = "/login-error.html", method=RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("types", typeService.getAllType());
		return "login.html";
	}
	
}
