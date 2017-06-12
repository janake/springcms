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
@RequestMapping(value = "/node")
public class NodeController {

	@Autowired
	NodeServiceInterface nodeService;

	@Autowired
	UserService userService;

	@Bean
	@ModelAttribute("node")
	public NodeView nodeFactory() {
		return NodeView.getNode();
	}

	@RequestMapping(value = "/new")
	public String createNewNode(Model model) {
		addNodesToModel(model);
		model.addAttribute("fragment", "newNode.ftl");
		return "index";
	}

	@RequestMapping(value = "/createNewNode", method = RequestMethod.POST)
	public String saveNewNode(@Valid @ModelAttribute("node") NodeView node, Model model, BindingResult errors,
			Principal principal) {
		Long nodeId = 0l;

		if (errors.hasErrors()) {
			model.addAttribute("fragment", "newNode.ftl");
			return "index";
		}

		String userName = principal.getName();
		UserView user = userService.findByuserNameOrEmail(userName);
		node.setCreatedBy(user);
		nodeId = nodeService.save(node);

		return "redirect:/node/" + nodeId;
	}

	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	public String editNode(@Valid NodeView node, BindingResult errors, Model model) {

		addNodesToModel(model);
		if (errors.hasErrors()) {
			model.addAttribute("fragment", "editNode.ftl");
			return "index";
		}

		nodeService.update(node);
		return "redirect:/node/" + node.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getNode(@PathVariable Long id, Model model) throws EntityNotFoundException {
		addCurrentNodeToModel(id, model);
		addNodesToModel(model);
		model.addAttribute("fragment", "node.ftl");
		return "index";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
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
