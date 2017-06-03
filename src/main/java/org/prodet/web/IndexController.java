package org.prodet.web;

import java.util.ArrayList;

import org.prodet.service.NodeService;
import org.prodet.service.DAO.NodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
		
	@Autowired
	NodeService nodeService;

	@RequestMapping(
		value = "/",
		method = RequestMethod.GET
	)
	public String getIndex(Model model) {
		
		ArrayList<NodeDao> nodes = nodeService.getAllNodes();

		model.addAttribute("nodes", nodes);
		
		return "index";
	}
	
}
