package org.prodet.service;

import java.util.ArrayList;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.prodet.repository.repository.NodeRepositoryInterface;
import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.domain.Node;
import org.prodet.service.DAO.NodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService {
	
	@Autowired
	private NodeRepositoryInterface nodeRepository;
	
	public NodeService() {
	}

	public Long save(NodeDao nodeFromController) {
		Node node = new Node();
		node.setTitle(nodeFromController.getTitle());
		node.setBody(nodeFromController.getBody());
		Node response = nodeRepository.save(node);
		return response.getId();		
	}

	@Transactional
	public Long update(NodeDao nodeDao) {
		Node node = nodeRepository.findOne(nodeDao.getId());
		
		node.setBody(nodeDao.getBody());
		node.setTitle(nodeDao.getTitle());
		
		nodeRepository.save(node);
		
		return nodeDao.getId();
	}
	
	public NodeDao getNode(Long id) {
		NodeDao node = new NodeDao();
		try {
			Node response = nodeRepository.findOne(id);
			String title = response.getTitle();
			String body = response.getBody();
			Long nodeId = response.getId();
			node = new NodeDao(nodeId, title, body);			
		} catch (Exception e) {
			throw new EntityNotFoundException(Node.class, id);
		}
		return node;
	}

	public ArrayList<NodeDao> getAllNodes() {
		Iterable<Node> nodes = nodeRepository.findAll();
		 ArrayList<NodeDao> nodeList = nodeToNodeDAOs(nodes);
		return nodeList;
	}

	private ArrayList<NodeDao> nodeToNodeDAOs(Iterable<Node> nodes) {
		ArrayList<NodeDao> nodeList = new ArrayList<NodeDao>();
		Iterator<Node> nodeIterator = nodes.iterator();
		
		while(nodeIterator.hasNext()) {
			Node node = nodeIterator.next();
			nodeList.add(nodeToNodeDAO(node));
		}
		
		return nodeList;
	}
	
	private NodeDao nodeToNodeDAO(Node node) {
		
		Long nid = node.getId();
		String title = node.getTitle();
		String body = node.getBody();
		
		return new NodeDao(nid, title, body);
	}
	
}
