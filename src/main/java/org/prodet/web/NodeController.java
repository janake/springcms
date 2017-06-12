package org.prodet.web;

import java.util.ArrayList;

import javax.validation.Valid;

import org.prodet.service.NodeService;
import org.prodet.service.DAO.NodeDao;
import org.prodet.service.DAO.NodeDaos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/node")
public class NodeController {
	
	@Autowired
	NodeService nodeService;
	
	@Autowired
	NodeDaos nodeDAOs;
	
	@Bean
	@ModelAttribute("node")
	public NodeDao nodeFactory() {
		return NodeDao.getNode();
	}

	@RequestMapping(value="/new")
	public String createNewNode(Model model) {
		addNodesToModel(model);
		return "newNode";		
	}
	
	
	@RequestMapping(value="/createNewNode", method=RequestMethod.POST)
	public String saveNewNode(@Valid @ModelAttribute("node") NodeDao node, Model model, BindingResult errors) {
		
		Long nodeId = 0l;
		
		if( errors.hasErrors() ) {
	        return "newNode";
	    }
		
		nodeId = nodeService.save(node);			
		return "redirect:/node/" + nodeId;
	}
	
	@RequestMapping(value="/{id}/save",method=RequestMethod.POST)
		public String editNode(@Valid NodeDao node, BindingResult errors, Model model) {
		
			addNodesToModel(model);				
			if( errors.hasErrors() ) {
		        return "editNode";
		    }

			nodeService.update(node);
			return "redirect:/node/" + node.getId();
		}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getNode(@PathVariable Long id, Model model) {
		try {			
			addCurrentNodeToModel(id, model);
			addNodesToModel(model);			
			return "node";
		} catch (Exception e) {
			throw e;
		}
	}

	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
	public String setNode(@PathVariable Long id, Model model) {
		
		addCurrentNodeToModel(id, model);
		addNodesToModel(model);
		
		return "editNode";
	}

	private void addCurrentNodeToModel(Long id, Model model) {
		NodeDao node = nodeService.getNode(id);
		model.addAttribute("node", node);
	}
	
	private void addNodesToModel(Model model) {
		ArrayList<NodeDao> nodes = nodeDAOs.getAllNodes();
		model.addAttribute("nodes", nodes);
	}

}
