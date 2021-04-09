package org.prodet.service;

import org.prodet.configuration.security.CustomUserDetails;
import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.prodet.repository.dao.Visibility;
import org.prodet.repository.repository.TypeRepositoryInterface;
import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepositoryInterface typeRepo;

    @Autowired
    private UserService userService;

    public List<TypeDTO> getAllType(Principal principal) {

        Iterable<Type> types;
        if (principal != null) {
            User user = userService.getUserFromPrincipal(principal);
            types = typeRepo.findAllByUser(user);
        } else {
            types = typeRepo.findAllByPublic();
        }

        List<TypeDTO> typeDTOS = new ArrayList<>();
        types.forEach(
                v -> {
                    typeDTOS.add(new TypeDTO(v));
                }
        );
        return typeDTOS;
    }

    public void addTypesToModel(Model model, Principal principal) {
        Iterable<Type> types;
        if (principal != null) {
            User user = userService.getUserFromPrincipal(principal);
            types = typeRepo.findAllByUser(user);
        } else {
            types = typeRepo.findAllByPublic();
        }

        List<TypeDTO> typeDTOS = new ArrayList<>();
        types.forEach(
                v -> {
                    typeDTOS.add(new TypeDTO(v));
                }
        );
        model.addAttribute("types", typeDTOS);
    }

    public TypeDTO getTypeByName(String typeName) {
        Type type = typeRepo.findByName(typeName);
        return new TypeDTO(type);
    }

    public void save(List<TypeDTO> validTypeListDTOs) {
        validTypeListDTOs.stream().forEach(v ->
                typeRepo.save(new Type(v)));
    }

    public void save(TypeDTO typeDTO, Principal principal) {
        UserDTO user = new UserDTO(userService.getUserFromPrincipal(principal));
        typeDTO.setCreatedBy(user);
        typeDTO.setEntityName("node");
        typeDTO.setVisibility(Visibility.Private);
        typeRepo.save(new Type(typeDTO));
    }
}
