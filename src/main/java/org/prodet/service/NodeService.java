package org.prodet.service;

import java.util.ArrayList;
import java.util.Iterator;

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
		Node node = nodeRepository.findOne(nodeDao.getId());
		
		node.setBody(nodeDao.getBody());
		node.setTitle(nodeDao.getTitle());
		
		nodeRepository.save(node);
		
		return nodeDao.getId();
	}
	
	@Override
	public NodeView getNode(Long id) throws EntityNotFoundException {
		NodeView node = new NodeView();
		try {
			Node response = nodeRepository.findOne(id);
			String title = response.getTitle();
			String body = response.getBody();
			Long nodeId = response.getId();
			User createdBy = response.getCreatedBy();
			UserView userView = new UserView(createdBy);
			node = new NodeView(nodeId, title, body, userView);
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
