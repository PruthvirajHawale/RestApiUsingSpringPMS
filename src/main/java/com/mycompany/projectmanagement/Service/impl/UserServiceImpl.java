package com.mycompany.projectmanagement.Service.impl;

import com.mycompany.projectmanagement.Converter.UserConverter;
import com.mycompany.projectmanagement.Repository.UserRepository;
import com.mycompany.projectmanagement.Service.UserService;
import com.mycompany.projectmanagement.dto.UserDTO;
import com.mycompany.projectmanagement.entity.UserEntity;
import com.mycompany.projectmanagement.execption.BusinessException;
import com.mycompany.projectmanagement.execption.ErrorModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        Optional<UserEntity> userEntity1 = userRepository.findByEmail(userDTO.getEmail());
        //Check is user is already registered
        if(userEntity1.isPresent()){
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ALREADY_EXISTS");
            errorModel.setMessage("please try to register with new email ID");
            errorModels.add(errorModel);
           throw new BusinessException(errorModels);
        }
        UserEntity userEntity = userConverter.ConvertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userConverter.ConvertEntitytoDTO(userEntity);
    }

    @Override
    public boolean loginUser(String email, String password) {
        Optional<UserEntity> userEntityResponse = userRepository.findByEmailAndPassword(email,password);
        if(userEntityResponse.isPresent()){
            return true;
        }else{
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect email or Password");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }

    }


}
