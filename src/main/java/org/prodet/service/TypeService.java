package org.prodet.service;

import org.prodet.repository.dao.Type;
import org.prodet.repository.repository.TypeRepositoryInterface;
import org.prodet.service.dto.TypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepositoryInterface typeRepo;

    public List<TypeDTO> getAllType() {
        Iterable<Type> types = typeRepo.findAll();
        List<TypeDTO> typeDTOS = new ArrayList<>();
        types.forEach(
                v -> {
                    typeDTOS.add(new TypeDTO(v));
                }
        );
        return typeDTOS;
    }

    public TypeDTO getTypeByName(String typeName) {
        Type type = typeRepo.findByName(typeName);
        return new TypeDTO(type);
    }
}
