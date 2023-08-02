package com.mycompany.projectmanagement.Converter;

import com.mycompany.projectmanagement.dto.PropertyDTO;
import com.mycompany.projectmanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity ConvertDTOtoEntity(PropertyDTO propertyDTO){
        PropertyEntity p1 = new PropertyEntity();
        p1.setTitle(propertyDTO.getTitle());
        p1.setDescription(propertyDTO.getDescription());
        p1.setAddress(propertyDTO.getAddress());
        p1.setOnwerEmail(propertyDTO.getOnwerEmail());
        p1.setOwnerName(propertyDTO.getOwnerName());
        p1.setPrice(propertyDTO.getPrice());

        return p1;
    }

    public PropertyDTO convertEntitytoDTO(PropertyEntity propertyEntity){
        PropertyDTO p1 = new PropertyDTO();

        p1.setId(propertyEntity.getId());
        p1.setTitle(propertyEntity.getTitle());
        p1.setDescription(propertyEntity.getDescription());
        p1.setAddress(propertyEntity.getAddress());
        p1.setOnwerEmail(propertyEntity.getOnwerEmail());
        p1.setOwnerName(propertyEntity.getOwnerName());
        p1.setPrice(propertyEntity.getPrice());
        return p1;
    }

}
