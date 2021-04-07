package org.prodet.service;

import org.prodet.repository.dao.Type;
import org.prodet.repository.repository.TypeRepositoryInterface;
import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.TypeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService implements AdminServiceInterface{

    @Autowired
    private TypeService typeService;

    @Override
    public List<TypeDTO> getValidTypeListDTOs(TypeListDTO controllerTypeListDTO, Principal principal) {
        TypeListDTO typeListDTO = new TypeListDTO(typeService.getAllType(principal));
        Map<Long, TypeDTO> origTypeDTOs = typeListDTO.getTypeDTOs().stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
        Map<Long, TypeDTO> newTypeDTOs = controllerTypeListDTO.getTypeDTOs().stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
        return origTypeDTOs.entrySet().stream().filter(v -> {
            return (newTypeDTOs.get(v.getKey()).getTypeName().equals(v.getValue().getTypeName()) &&
                    newTypeDTOs.get(v.getKey()).getEntityName().equals(v.getValue().getEntityName()) &&
                    newTypeDTOs.get(v.getKey()).getCreatedBy().getId() == (v.getValue().getCreatedBy().getId()) &&
                    (newTypeDTOs.get(v.getKey()).getId() == (v.getValue().getId())));
        }).map(v -> newTypeDTOs.get(v.getKey())).collect(Collectors.toList());
    }
}
