package org.prodet.service.dto;

import java.util.ArrayList;
import java.util.List;

public class TypeListDTO {

    private List<TypeDTO> typeDTOs;

    public TypeListDTO() {
    }

    public TypeListDTO(List<TypeDTO> types) {
        this.typeDTOs = types;
    }

    public List<TypeDTO> getTypeDTOs() {
        return typeDTOs;
    }

    public void setTypeDTOs(List<TypeDTO> types) {
        this.typeDTOs = new ArrayList<>(types);
    }

}
