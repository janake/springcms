package org.prodet.service;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.dao.Node;
import org.prodet.service.dto.NodeDTO;
import org.prodet.service.dto.TypeDTO;

import java.security.Principal;
import java.util.ArrayList;

public interface NodeServiceInterface {

	Long save(Node nodeFromController);

	Long update(NodeDTO nodeDao);

	NodeDTO getNode(long id, Principal principal) throws EntityNotFoundException;

	ArrayList<NodeDTO> getAllNodes();

    ArrayList<NodeDTO> getAllNodesByType(TypeDTO type, Principal principal);
}