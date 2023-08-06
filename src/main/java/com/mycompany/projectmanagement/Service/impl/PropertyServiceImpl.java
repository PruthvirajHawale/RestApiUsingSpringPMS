package com.mycompany.projectmanagement.Service.impl;

import com.mycompany.projectmanagement.Converter.PropertyConverter;
import com.mycompany.projectmanagement.Repository.PropertyRepository;
import com.mycompany.projectmanagement.Repository.UserRepository;
import com.mycompany.projectmanagement.Service.PropertyService;
import com.mycompany.projectmanagement.controller.PropertyController;
import com.mycompany.projectmanagement.dto.PropertyDTO;
import com.mycompany.projectmanagement.entity.PropertyEntity;
import com.mycompany.projectmanagement.entity.UserEntity;
import com.mycompany.projectmanagement.execption.BusinessException;
import com.mycompany.projectmanagement.execption.ErrorModel;
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

    @Autowired
    private UserRepository userRepository;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> optEnty = userRepository.findById(propertyDTO.getUserId());

        if(optEnty.isPresent()) {

            PropertyEntity pe = propertyConverter.ConvertDTOtoEntity(propertyDTO);
            pe.setUserEntity(optEnty.get());
            pe = propertyRepository.save(pe);
            propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        }else {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_DOES_NOT_EXISTS");
            errorModel.setMessage("Register  first to add property");
            List<ErrorModel> errorModels = new ArrayList<>();
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllPropertyById(Long userId){
        List<PropertyEntity> listOfPropertiesOfUsers = propertyRepository.findByUserEntityId(userId);
        List<PropertyDTO> propertyDTOS = new ArrayList<>();
        for(PropertyEntity pe : listOfPropertiesOfUsers){
            propertyDTOS.add(propertyConverter.convertEntitytoDTO(pe));
        }
        return propertyDTOS;
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
