package org.prodet.web;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.service.NodeServiceInterface;
import org.prodet.service.UserService;
import org.prodet.service.dao.NodeView;
import org.prodet.service.dao.UserView;
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
	
	private ArrayList<NodeView> nodes = new ArrayList<>();
	
	@Autowired
	NodeServiceInterface nodeService;
	
	@Autowired
	UserService userService;
	
	@Bean
	@ModelAttribute("node")
	public NodeView nodeFactory() {
		return NodeView.getNode();
	}

	@RequestMapping(value="/new")
	public String createNewNode(Model model) {
		addNodesToModel(model);
		model.addAttribute("fragment", "newNode.ftl");
		return "index";		
	}
	
	
	@RequestMapping(value="/createNewNode", method=RequestMethod.POST)
<<<<<<< 8689e058ab6e38f0af7ef48f1191b5b971ab737f
<<<<<<< 9d551ea8ca5930a9dbf780b2da6add13db08f894
	public String saveNewNode(@Valid @ModelAttribute("node") NodeDao node, Model model, BindingResult errors) {
=======
	public String saveNewNode(@Valid @ModelAttribute("node") NodeView node, 
			Model model, BindingResult errors, Principal principal) {
>>>>>>> new entitytype
=======
<<<<<<< 044969d9421f0bfec74ec8bd4824435da0c3a6d3
	public String saveNewNode(@Valid @ModelAttribute("node") NodeView node, 
			Model model, BindingResult errors, Principal principal) {
=======
	public String saveNewNode(@Valid @ModelAttribute("node") NodeDao node, Model model, BindingResult errors) {
>>>>>>> add model to nodecontroller
>>>>>>> add model to nodecontroller
		
		Long nodeId = 0l;
		
		if( errors.hasErrors() ) {
			model.addAttribute("fragment", "newNode.ftl");
			return "index";
	    }
		
		String userName = principal.getName();
		UserView user = userService.findByuserNameOrEmail(userName);
		node.setCreatedBy(user);
		nodeId = nodeService.save(node);
		
		return "redirect:/node/" + nodeId;
	}
	
	@RequestMapping(value="/{id}/save",method=RequestMethod.POST)
		public String editNode(@Valid NodeView node, 
				BindingResult errors, Model model) {
		
			addNodesToModel(model);				
			if( errors.hasErrors() ) {
				model.addAttribute("fragment", "editNode.ftl");
				return "index";
		    }
			
			nodeService.update(node);
			return "redirect:/node/" + node.getId();
		}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getNode(@PathVariable Long id, Model model) throws EntityNotFoundException {
		addCurrentNodeToModel(id, model);
		addNodesToModel(model);
		model.addAttribute("fragment", "node.ftl");
		return "index";
	}

	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
	public String setNode(@PathVariable Long id, Model model) throws EntityNotFoundException {
		
		addCurrentNodeToModel(id, model);
		addNodesToModel(model);
		
		model.addAttribute("fragment", "editNode.ftl");
		return "index";
		
	}

	private void addCurrentNodeToModel(Long id, Model model) throws EntityNotFoundException {
		NodeView node = nodeService.getNode(id);
		model.addAttribute("node", node);
	}
	
	private void addNodesToModel(Model model) {
		ArrayList<NodeView> nodes = nodeService.getAllNodes();
		model.addAttribute("nodes", nodes);
	}

}
