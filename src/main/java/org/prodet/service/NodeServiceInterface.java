package org.prodet.service;

import java.util.ArrayList;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.service.dao.NodeView;

public interface NodeServiceInterface {

	Long save(NodeView nodeFromController);

	Long update(NodeView nodeDao);

	NodeView getNode(Long id) throws EntityNotFoundException;

	ArrayList<NodeView> getAllNodes();

}