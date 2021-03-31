package org.prodet.service;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.dao.Node;
import org.prodet.service.dto.NodeDTO;
import org.prodet.service.dto.TypeDTO;

import java.util.ArrayList;

public interface NodeServiceInterface {

	Long save(Node nodeFromController);

	Long update(NodeDTO nodeDao);

	NodeDTO getNode(Long id) throws EntityNotFoundException;

	ArrayList<NodeDTO> getAllNodes();

    ArrayList<NodeDTO> getAllNodesByType(TypeDTO type);
}