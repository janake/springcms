package org.prodet.web;

import java.security.Principal;
import java.util.ArrayList;
import javax.validation.Valid;
import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.domain.Node;
import org.prodet.repository.domain.Type;
import org.prodet.repository.domain.User;
import org.prodet.service.NodeServiceInterface;
import org.prodet.service.TypeService;
import org.prodet.service.UserService;
import org.prodet.service.dao.NodeView;
import org.prodet.service.dao.TypeView;
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
	private NodeServiceInterface nodeService;

	@Autowired
	private UserService userService;

	@Autowired
	private TypeService typeService;

	@Bean
	@ModelAttribute("node")
	public Node nodeFactory() {
		return new Node();
	}

	@RequestMapping(value = "/{type}/new")
	public String createNewNode(@PathVariable String type, Model model) {
		TypeView typeView = typeService.getTypeByName(type);
		addNodesToModel(model, typeView);
		addTypesToModel(model);
		addTypeToModel(model, typeView);
		return "newnode";
	}

	@RequestMapping(value = "/{typeName}/createNewNode", method = RequestMethod.POST)
	public String saveNewNode(@PathVariable("typeName") String typeName, @Valid @ModelAttribute("node") Node node, Model model, BindingResult errors,
			Principal principal) {
		TypeView typeView = typeService.getTypeByName(typeName);
		addTypeToModel(model, typeView);
		Long nodeId;
		String userName = principal.getName();
		User user = userService.findByuserNameOrEmail(userName);
		node.setCreatedBy(user);
		node.setType(new Type(typeView));
		nodeId = nodeService.save(node);

		return "redirect:/node/" + nodeId;
	}

	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	public String editNode(@Valid NodeView node, @PathVariable Long id, Model model) {

		//TODO:: need to check for security perspective
		node.setId(id);

		nodeService.update(node);
		return "redirect:/node/" + node.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getNode(@PathVariable Long id, Model model) throws EntityNotFoundException {
		NodeView node = nodeService.getNode(id);
		addCurrentNodeToModel(node, model);
		addNodesToModel(model, node.getType());
		addTypesToModel(model);
		addTypeToModel(model, node.getType());
		return "/node";
	}

	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
	public String getNodeByType(@PathVariable String type, Model model) throws EntityNotFoundException {
		TypeView typeView = typeService.getTypeByName(type);
		addNodesToModel(model, typeView);
		addTypesToModel(model);
		addTypeToModel(model, typeView);
		return "/node";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String setNode(@PathVariable Long id, Model model) throws EntityNotFoundException {
		NodeView node = nodeService.getNode(id);
		addCurrentNodeToModel(node, model);
		addNodesToModel(model, node.getType());
		addTypesToModel(model);
		return "editnode";

	}

	private void addCurrentNodeToModel(NodeView node, Model model) throws EntityNotFoundException {
		model.addAttribute("node", node);
		TypeView typeView = node.getType();
		addTypeToModel(model, typeView);
	}

	private void addTypesToModel(Model model) {
		model.addAttribute("types", typeService.getAllType());
	}

	private void addNodesToModel(Model model, TypeView type) {
		ArrayList<NodeView> nodes = nodeService.getAllNodesByType(type);
		model.addAttribute("nodes", nodes);
	}

	private void addTypeToModel(Model model, TypeView typeView) {
		model.addAttribute("typeView", typeView);
	}



}
