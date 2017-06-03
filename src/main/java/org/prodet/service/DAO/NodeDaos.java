package org.prodet.service.DAO;

import java.util.ArrayList;

import org.prodet.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NodeDaos {
	
	@Autowired
	NodeService nodeService;
	
	public ArrayList<NodeDao> getAllNodes() {
		ArrayList<NodeDao> nodes = nodeService.getAllNodes();
		return nodes;
	}
	
}
