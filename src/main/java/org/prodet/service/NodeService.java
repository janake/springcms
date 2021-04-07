package org.prodet.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.configuration.security.CustomUserDetails;
import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.prodet.repository.repository.NodeRepositoryInterface;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.prodet.service.dto.NodeDTO;
import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.TypeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class NodeService implements NodeServiceInterface {

	@Autowired
	private UserService userService;

	@Autowired
	private NodeRepositoryInterface nodeRepository;

	@Autowired
	private TypeService typeService;

	public NodeService() {
	}

	@Override
	public Long save(Node nodeFromController) {
		Node node = new Node();
		node.setBody(nodeFromController.getBody());
		node.setTitle(nodeFromController.getTitle());
		User user = userService.findByuserNameOrEmail(nodeFromController.getCreatedBy().getEmail());
		node.setCreatedBy(user);
		node.setType(nodeFromController.getType());
		node.setCreatedDate();
		node.setVisibility(nodeFromController.getVisibility());
		Node response = nodeRepository.save(node);
		return response.getId();		
	}

	@Override
	@Transactional
	public Long update(NodeDTO nodeDao) {
		Optional<Node> node = nodeRepository.findById(nodeDao.getId());

		if (node.isPresent()) {
			node.get().setBody(nodeDao.getBody());
			node.get().setTitle(nodeDao.getTitle());
			node.get().setVisibility(nodeDao.getVisibility());
			nodeRepository.save(node.get());
		}

		return nodeDao.getId();
	}
	
	@Override
	public NodeDTO getNode(long id, Principal principal) throws EntityNotFoundException {
		NodeDTO node = new NodeDTO();
		try {
			Optional<Node> response;

			if (principal != null) {
				User user = userService.getUserFromPrincipal(principal);
				response = nodeRepository.findNodeById(id, user);
			} else {
				response = nodeRepository.findNodeById(id);
			}
			if (response.isPresent()) {
				node = new NodeDTO(response.get());
			} else {
				throw new RuntimeException("Node not found.");
			}
		} catch (Exception e) {
			throw new EntityNotFoundException(Node.class, id);
		}
		return node;
	}

	@Override
	public ArrayList<NodeDTO> getAllNodes() {
		Iterable<Node> nodes = nodeRepository.findAll();
		 ArrayList<NodeDTO> nodeList = nodeToNodeDAOs(nodes);
		return nodeList;
	}

	@Override
	public ArrayList<NodeDTO> getAllNodesByType(TypeDTO typeDTO, Principal principal) {
		ArrayList<NodeDTO> nodeList = null;
		if (principal != null) {
			User user = userService.getUserFromPrincipal(principal);
			Iterable<Node> nodes = nodeRepository.findAllByType(new Type(typeDTO), user);
			nodeList = nodeToNodeDAOs(nodes);
		} else {
			Iterable<Node> nodes = nodeRepository.findAllByType(new Type(typeDTO));
			nodeList = nodeToNodeDAOs(nodes);
		}
		return nodeList;
	}

	private ArrayList<NodeDTO> nodeToNodeDAOs(Iterable<Node> nodes) {
		ArrayList<NodeDTO> nodeList = new ArrayList<NodeDTO>();
		Iterator<Node> nodeIterator = nodes.iterator();
		
		while(nodeIterator.hasNext()) {
			Node node = nodeIterator.next();
			nodeList.add(nodeToNodeDAO(node));
		}
		
		return nodeList;
	}

	public void addNodeToModel(NodeDTO node, Model model) {
		model.addAttribute("node", node);
	}

	 public void addNodeToModel(NodeDTO node, TypeDTO typeDTO, Model model) {
		model.addAttribute("node", node);
		addTypeToModel(model, typeDTO);
	}

	public TypeDTO getTypeDTOByType(String type) {
		return typeService.getTypeByName(type);
	}

	@Override
	public void addTypesToModel(TypeListDTO types, Model model, Principal principal) {
		types.setTypeDTOs(typeService.getAllType(principal));
		model.addAttribute("typeform", types);
	}

	public void addTypeToModel(Model model, String type) {
		TypeDTO typeDTO = typeService.getTypeByName(type);
		model.addAttribute("typeDTO", typeDTO);
	}

	public void addTypeToModel(Model model, TypeDTO typeDTO) {
		model.addAttribute("typeDTO", typeDTO);
	}

	public void addNodesToModel(Model model, String type, Principal principal) {
		TypeDTO typeDTO = getTypeDTOByType(type);
		addNodesToModel(model, typeDTO, principal);
	}

	public void addNodesToModel(Model model, TypeDTO typeDTO, Principal principal) {
		ArrayList<NodeDTO> nodes = getAllNodesByType(typeDTO, principal);
		model.addAttribute("nodes", nodes);
	}

	@Override
	public void addTypesToModel(Model model, Principal principal) {
		model.addAttribute("types", typeService.getAllType(principal));
	}

	private NodeDTO nodeToNodeDAO(Node node) {
		return new NodeDTO(node);
	}

}
