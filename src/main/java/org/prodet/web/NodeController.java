package org.prodet.web;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.prodet.service.NodeServiceInterface;
import org.prodet.service.UserService;
import org.prodet.service.dto.NodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/node")
public class NodeController {

	@Autowired
	private NodeServiceInterface nodeService;

	@Autowired
	private UserService userService;

	@Bean
	@ModelAttribute("node")
	public Node nodeFactory() {
		return new Node();
	}

	@RequestMapping(value = "/{type}/new")
	public String createNewNode(@PathVariable String type, Model model, Principal principal) {
		nodeService.addNodesToModel(model, type, principal);
		nodeService.addTypesToModel(model, principal);
		nodeService.addTypeToModel(model, type);
		return "newnode";
	}

	@RequestMapping(value = "/{typeName}/createNewNode", method = RequestMethod.POST)
	public String saveNewNode(@PathVariable("typeName") String type, @Valid @ModelAttribute("node") Node node, Model model, BindingResult errors,
			Principal principal) {
		nodeService.addTypeToModel(model, type);
		Long nodeId;
		String userName = principal.getName();
		User user = userService.findByuserNameOrEmail(userName);
		node.setCreatedBy(user);
		node.setType(new Type(nodeService.getTypeDTOByType(type)));
		nodeId = nodeService.save(node);

		return "redirect:/node/" + nodeId;
	}

	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	public String editNode(@Valid NodeDTO node, @PathVariable Long id, Model model) {

		//TODO:: need to check for security perspective
		node.setId(id);

		nodeService.update(node);
		return "redirect:/node/" + node.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getNode(@PathVariable Long id, Model model, Principal principal) throws EntityNotFoundException {
		NodeDTO node = nodeService.getNode(id, principal);
		User user = userService.getUserFromPrincipal(principal);
		nodeService.addNodeToModel(node, model);
		nodeService.addNodesToModel(model, node.getType(), principal);
		nodeService.addTypesToModel(model, principal);
		nodeService.addTypeToModel(model, node.getType());
		nodeService.isEditableByCurrentUser(id, model, user);
		return "node";
	}

	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
	public String getNodeByType(@PathVariable String type, Model model, Principal principal) throws EntityNotFoundException {
		nodeService.addNodesToModel(model, type, principal);
		nodeService.addTypesToModel(model, principal);
		nodeService.addTypeToModel(model, type);
		return "node";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String setNode(@PathVariable Long id, Model model, Principal principal) throws EntityNotFoundException, IllegalAccessException {
		User user = userService.getUserFromPrincipal(principal);
		NodeDTO node = nodeService.getNode(id, principal);
		if (node.getCreatedBy().getId() != user.getId()) {
			throw new IllegalAccessException("You do not have rights to edit this node.");
		}
		nodeService.addNodeToModel(node, model);
		nodeService.addTypeToModel(model, node.getType());
		nodeService.addNodesToModel(model, node.getType(), principal);
		nodeService.addTypesToModel(model, principal);
		return "editnode";
	}
}
