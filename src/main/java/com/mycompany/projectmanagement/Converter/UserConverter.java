package com.mycompany.projectmanagement.Converter;

import com.mycompany.projectmanagement.dto.UserDTO;
import com.mycompany.projectmanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component //Makes class Singleton and handles by spring
public class UserConverter {


    public UserEntity ConvertDTOtoEntity(UserDTO userDTO){
        UserEntity p1 = new UserEntity();
        p1.setEmail(userDTO.getEmail());
        p1.setFirstName(userDTO.getFirstName());
        p1.setLastName(userDTO.getLastName());
        p1.setPassword(userDTO.getPassword());
        return p1;
    }

    public UserDTO ConvertEntitytoDTO(UserEntity userEntity){
        UserDTO p1 = new UserDTO();
        p1.setEmail(userEntity.getEmail());
        p1.setFirstName(userEntity.getFirstName());
        p1.setLastName(userEntity.getLastName());
//        p1.setPassword(userEntity.getPassword());
        return p1;
    }
}
