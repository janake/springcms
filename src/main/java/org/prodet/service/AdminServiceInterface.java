package org.prodet.service;

import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.TypeListDTO;

import java.security.Principal;
import java.util.List;

public interface AdminServiceInterface {
    List<TypeDTO> getValidTypeListDTOs(TypeListDTO typeListDTO, Principal principal);
}
