package org.prodet.web;

import java.util.ArrayList;

import org.prodet.service.NodeServiceInterface;
import org.prodet.service.dto.NodeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin", method=RequestMethod.GET)
public class AdminController {
	
	@RequestMapping(value="/config", method=RequestMethod.GET)
	public String getAdmin(Model model, NodeServiceInterface nodeService) {
		
		ArrayList<NodeDTO> allCurrentNodes = nodeService.getAllNodes();
		model.addAttribute("nodes", allCurrentNodes);
		model.addAttribute("fragment", "admin.ftl");
		
		return "index";
	}
	
}
