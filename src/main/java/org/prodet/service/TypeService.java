package org.prodet.service;

import org.prodet.repository.domain.Type;
import org.prodet.repository.repository.TypeRepositoryInterface;
import org.prodet.service.dao.TypeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepositoryInterface typeRepo;

    public List<TypeView> getAllType() {
        Iterable<Type> types = typeRepo.findAll();
        List<TypeView> typeViews = new ArrayList<>();
        types.forEach(
                v -> {
                    typeViews.add(new TypeView(v));
                }
        );
        return typeViews;
    }

    public TypeView getTypeByName(String typeName) {
        Type type = typeRepo.findByName(typeName);
        return new TypeView(type);
    }
}
