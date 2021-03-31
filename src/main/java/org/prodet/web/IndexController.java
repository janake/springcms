package org.prodet.web;

import java.util.ArrayList;

import org.prodet.service.NodeServiceInterface;
import org.prodet.service.TypeService;
import org.prodet.service.dto.NodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
		
	@Autowired
	private NodeServiceInterface nodeService;

	@Autowired
	private TypeService typeService;

	@RequestMapping(
		value = "/",
		method = RequestMethod.GET
	)
	public String getIndex(Model model) {
		ArrayList<NodeDTO> nodes = nodeService.getAllNodes();
		model.addAttribute("nodes", nodes);
		model.addAttribute("types", typeService.getAllType());
		return "index";
	}
	
}
