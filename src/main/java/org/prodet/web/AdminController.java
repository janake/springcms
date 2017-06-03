package org.prodet.web;

import java.util.ArrayList;

import org.prodet.service.DAO.NodeDao;
import org.prodet.service.DAO.NodeDaos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin", method=RequestMethod.GET)
public class AdminController {
	
	@Autowired
	NodeDaos nodeDAOs;

	@RequestMapping(value="/config", method=RequestMethod.GET)
	public String getAdmin(Model model) {
		
		ArrayList<NodeDao> allCurrentNodes = nodeDAOs.getAllNodes();
		model.addAttribute("nodes", allCurrentNodes);
		
		return "admin";
	}
	
}
