package org.prodet.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.domain.Node;
import org.prodet.repository.domain.User;
import org.prodet.repository.repository.NodeRepositoryInterface;
import org.prodet.service.dao.NodeView;
import org.prodet.service.dao.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService implements NodeServiceInterface {
	
	@Autowired
	private NodeRepositoryInterface nodeRepository;
	
	public NodeService() {
	}

	@Override
	public Long save(NodeView nodeFromController) {
		Node node = new Node();
		node.setBody(nodeFromController.getBody());
		node.setTitle(nodeFromController.getTitle());
		User user = new User(nodeFromController.getCreatedBy());
		node.setCreatedBy(user);
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
				String title = response.get().getTitle();
				String body = response.get().getBody();
				Long nodeId = response.get().getId();
				User createdBy = response.get().getCreatedBy();
				UserView userView = new UserView(createdBy);
				node = new NodeView(nodeId, title, body, userView);
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
		
		Long nid = node.getId();
		String title = node.getTitle();
		String body = node.getBody();
		User user = node.getCreatedBy();
		UserView createdBy = new UserView(user);	
		
		return new NodeView(nid, title, body, createdBy);
	}
	
}
