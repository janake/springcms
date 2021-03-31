package org.prodet.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.prodet.repository.repository.NodeRepositoryInterface;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.prodet.service.dto.NodeDTO;
import org.prodet.service.dto.TypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService implements NodeServiceInterface {

	@Autowired
	private UserRepositoryInterface userRepository;

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
		User user = userRepository.findByuserNameOrEmail(nodeFromController.getCreatedBy().getEmail());
		node.setCreatedBy(user);
		node.setType(nodeFromController.getType());
		node.setCreatedDate();
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
			nodeRepository.save(node.get());
		}

		return nodeDao.getId();
	}
	
	@Override
	public NodeDTO getNode(Long id) throws EntityNotFoundException {
		NodeDTO node = new NodeDTO();
		try {
			Optional<Node> response = nodeRepository.findById(id);
			if (response.isPresent()) {
				node = new NodeDTO(response.get());
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
	public ArrayList<NodeDTO> getAllNodesByType(TypeDTO typeDTO) {
		Iterable<Node> nodes = nodeRepository.findAllByType(new Type(typeDTO));
		ArrayList<NodeDTO> nodeList = nodeToNodeDAOs(nodes);
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
	
	private NodeDTO nodeToNodeDAO(Node node) {
		return new NodeDTO(node);
	}
	
}
