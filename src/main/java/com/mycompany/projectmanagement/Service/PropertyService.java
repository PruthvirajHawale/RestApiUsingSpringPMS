package com.mycompany.projectmanagement.Service;

import com.mycompany.projectmanagement.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {
     PropertyDTO saveProperty(PropertyDTO propertyDTO);
     List<PropertyDTO> getAllProperty();

    PropertyDTO  updateProperty(PropertyDTO propertyDTO, long propertyID);
    PropertyDTO  updatePropertyDescription(PropertyDTO propertyDTO, long propertyID);
    PropertyDTO  updatePropertyPrice(PropertyDTO propertyDTO, long propertyID);

    PropertyDTO deletePropertyById(Long id);

    List<PropertyDTO> getAllPropertyById(Long id);
}
