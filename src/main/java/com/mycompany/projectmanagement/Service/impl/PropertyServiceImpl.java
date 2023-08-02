package com.mycompany.projectmanagement.Service.impl;

import com.mycompany.projectmanagement.Converter.PropertyConverter;
import com.mycompany.projectmanagement.Repository.PropertyRepository;
import com.mycompany.projectmanagement.Service.PropertyService;
import com.mycompany.projectmanagement.controller.PropertyController;
import com.mycompany.projectmanagement.dto.PropertyDTO;
import com.mycompany.projectmanagement.entity.PropertyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity pe = propertyConverter.ConvertDTOtoEntity(propertyDTO);

        pe = propertyRepository.save(pe);
        propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperty() {
        List<PropertyEntity>  listOfPropertyEntity= (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();
        for(PropertyEntity pe:listOfPropertyEntity){
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, long propertyID) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyID);
        PropertyDTO dto = new PropertyDTO();
        if(optEn.isPresent()){
            PropertyEntity p1 = optEn.get();
            p1.setTitle(propertyDTO.getTitle());
            p1.setDescription(propertyDTO.getDescription());
            p1.setAddress(propertyDTO.getAddress());
            p1.setOnwerEmail(propertyDTO.getOnwerEmail());
            p1.setOwnerName(propertyDTO.getOwnerName());
            p1.setPrice(propertyDTO.getPrice());

            dto = propertyConverter.convertEntitytoDTO(p1);
            propertyRepository.save(p1);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, long propertyID) {
         Optional<PropertyEntity> pe = propertyRepository.findById(propertyID);
         PropertyDTO dto = new PropertyDTO();
         if(pe.isPresent()){
             PropertyEntity p1 = pe.get();

             p1.setDescription(propertyDTO.getDescription());


             dto = propertyConverter.convertEntitytoDTO(p1);
             propertyRepository.save(p1);
         }
         return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, long propertyID) {

        Optional<PropertyEntity> pe = propertyRepository.findById(propertyID);
        PropertyDTO dto = new PropertyDTO();
        if(pe.isPresent()){
            PropertyEntity p1 = pe.get();

            p1.setPrice(propertyDTO.getPrice());


            dto = propertyConverter.convertEntitytoDTO(p1);
            propertyRepository.save(p1);
        }
        return dto;
    }

    @Override
    public PropertyDTO deletePropertyById(Long id) {
        Optional<PropertyEntity> pe = propertyRepository.findById(id);
        if(pe.isPresent()){
            propertyRepository.deleteById(id);
        }
        return propertyConverter.convertEntitytoDTO(pe.get());
    }
}
