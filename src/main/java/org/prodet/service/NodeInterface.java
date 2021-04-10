package org.prodet.service;

import org.prodet.repository.dao.UserInterface;
import org.prodet.repository.dao.Visibility;
import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.UserDTO;

public interface NodeInterface {
    String getTitle();

    String getBody();

    Long getId();

    UserInterface getCreatedBy();

    Visibility getVisibility();
}
