package org.prodet.web;

import java.util.ArrayList;
import org.prodet.service.NodeServiceInterface;
import org.prodet.service.dao.NodeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@Autowired
	NodeServiceInterface nodeService;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(Model model) {
		
		ArrayList<NodeView> allCurrentNodes = nodeService.getAllNodes();

		model.addAttribute("nodes", allCurrentNodes);
		model.addAttribute("fragment", "login.ftl");
		
		return "index";
	}
	
}
