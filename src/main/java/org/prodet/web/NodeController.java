package org.prodet.web;

import java.security.Principal;
import java.util.ArrayList;
import javax.validation.Valid;
import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.prodet.service.NodeServiceInterface;
import org.prodet.service.TypeService;
import org.prodet.service.UserService;
import org.prodet.service.dto.NodeDTO;
import org.prodet.service.dto.TypeDTO;
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
		TypeDTO typeDTO = typeService.getTypeByName(type);
		addNodesToModel(model, typeDTO);
		addTypesToModel(model);
		addTypeToModel(model, typeDTO);
		return "newnode";
	}

	@RequestMapping(value = "/{typeName}/createNewNode", method = RequestMethod.POST)
	public String saveNewNode(@PathVariable("typeName") String typeName, @Valid @ModelAttribute("node") Node node, Model model, BindingResult errors,
			Principal principal) {
		TypeDTO typeDTO = typeService.getTypeByName(typeName);
		addTypeToModel(model, typeDTO);
		Long nodeId;
		String userName = principal.getName();
		User user = userService.findByuserNameOrEmail(userName);
		node.setCreatedBy(user);
		node.setType(new Type(typeDTO));
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
	public String getNode(@PathVariable Long id, Model model) throws EntityNotFoundException {
		NodeDTO node = nodeService.getNode(id);
		addCurrentNodeToModel(node, model);
		addNodesToModel(model, node.getType());
		addTypesToModel(model);
		addTypeToModel(model, node.getType());
		return "node";
	}

	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
	public String getNodeByType(@PathVariable String type, Model model) throws EntityNotFoundException {
		TypeDTO typeDTO = typeService.getTypeByName(type);
		addNodesToModel(model, typeDTO);
		addTypesToModel(model);
		addTypeToModel(model, typeDTO);
		return "node";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String setNode(@PathVariable Long id, Model model) throws EntityNotFoundException {
		NodeDTO node = nodeService.getNode(id);
		addCurrentNodeToModel(node, model);
		addNodesToModel(model, node.getType());
		addTypesToModel(model);
		return "editnode";

	}

	private void addCurrentNodeToModel(NodeDTO node, Model model) throws EntityNotFoundException {
		model.addAttribute("node", node);
		TypeDTO typeDTO = node.getType();
		addTypeToModel(model, typeDTO);
	}

	private void addTypesToModel(Model model) {
		model.addAttribute("types", typeService.getAllType());
	}

	private void addNodesToModel(Model model, TypeDTO type) {
		ArrayList<NodeDTO> nodes = nodeService.getAllNodesByType(type);
		model.addAttribute("nodes", nodes);
	}

	private void addTypeToModel(Model model, TypeDTO typeDTO) {
		model.addAttribute("typeDTO", typeDTO);
	}



}
