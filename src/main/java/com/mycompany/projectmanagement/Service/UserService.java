package com.mycompany.projectmanagement.Service;

import com.mycompany.projectmanagement.dto.UserDTO;
import com.mycompany.projectmanagement.entity.UserEntity;
import org.apache.catalina.User;

public interface UserService {
     UserDTO registerUser(UserDTO userDTO);

     boolean loginUser(String email, String password);
}

