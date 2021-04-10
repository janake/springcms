package org.prodet.repository.dao;

import java.time.LocalDateTime;
import java.util.List;

public interface UserInterface {
    long getId();

    String getUserName();

    String getEmail();

    String getFirstName();

    String getLastName();

    LocalDateTime getCreatedDate();

    List<Role> getRoles();
}
