package org.prodet.service;

import org.prodet.configuration.EntityNotFoundException;
import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.User;
import org.prodet.service.dto.NodeDTO;
import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.TypeListDTO;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;

public interface NodeServiceInterface {

	Long save(Node nodeFromController);

	Long update(NodeDTO nodeDao);

	NodeDTO getNode(long id, Principal principal) throws EntityNotFoundException;

	ArrayList<NodeDTO> getAllNodesByType(TypeDTO typeDTO, Principal principal);

	ArrayList<NodeDTO> getAllNodes();

    void addNodesToModel(Model model, String type, Principal principal);

	void addNodesToModel(Model model, TypeDTO typeDTO, Principal principal);

	void addTypesToModel(Model model);

	void addTypeToModel(Model model, TypeDTO typeDTO);

	void addTypeToModel(Model model, String type);

	void addNodeToModel(NodeDTO node, Model model);

	TypeDTO getTypeDTOByType(String type);

    void addTypesToModel(TypeListDTO types, Model model);
}