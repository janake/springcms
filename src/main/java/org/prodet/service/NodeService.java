package org.prodet.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.domain.Node;
import org.prodet.repository.domain.Type;
import org.prodet.repository.domain.User;
import org.prodet.repository.repository.NodeRepositoryInterface;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.prodet.service.dao.NodeView;
import org.prodet.service.dao.TypeView;
import org.prodet.service.dao.UserView;
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
	public Long update(NodeView nodeDao) {
		Optional<Node> node = nodeRepository.findById(nodeDao.getId());

		if (node.isPresent()) {
			node.get().setBody(nodeDao.getBody());
			node.get().setTitle(nodeDao.getTitle());
			nodeRepository.save(node.get());
		}

		return nodeDao.getId();
	}
	
	@Override
	public NodeView getNode(Long id) throws EntityNotFoundException {
		NodeView node = new NodeView();
		try {
			Optional<Node> response = nodeRepository.findById(id);
			if (response.isPresent()) {
				node = new NodeView(response.get());
			}
		} catch (Exception e) {
			throw new EntityNotFoundException(Node.class, id);
		}
		return node;
	}

	@Override
	public ArrayList<NodeView> getAllNodes() {
		Iterable<Node> nodes = nodeRepository.findAll();
		 ArrayList<NodeView> nodeList = nodeToNodeDAOs(nodes);
		return nodeList;
	}

	@Override
	public ArrayList<NodeView> getAllNodesByType(TypeView typeView) {
		Iterable<Node> nodes = nodeRepository.findAllByType(new Type(typeView));
		ArrayList<NodeView> nodeList = nodeToNodeDAOs(nodes);
		return nodeList;
	}

	private ArrayList<NodeView> nodeToNodeDAOs(Iterable<Node> nodes) {
		ArrayList<NodeView> nodeList = new ArrayList<NodeView>();
		Iterator<Node> nodeIterator = nodes.iterator();
		
		while(nodeIterator.hasNext()) {
			Node node = nodeIterator.next();
			nodeList.add(nodeToNodeDAO(node));
		}
		
		return nodeList;
	}
	
	private NodeView nodeToNodeDAO(Node node) {
		return new NodeView(node);
	}
	
}
