package org.prodet.service;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.domain.Node;
import org.prodet.service.dao.NodeView;
import org.prodet.service.dao.TypeView;

import java.util.ArrayList;

public interface NodeServiceInterface {

	Long save(Node nodeFromController);

	Long update(NodeView nodeDao);

	NodeView getNode(Long id) throws EntityNotFoundException;

	ArrayList<NodeView> getAllNodes();

    ArrayList<NodeView> getAllNodesByType(TypeView type);
}