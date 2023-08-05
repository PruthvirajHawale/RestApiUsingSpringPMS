package com.mycompany.projectmanagement.controller;


import com.mycompany.projectmanagement.Service.UserService;
import com.mycompany.projectmanagement.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vi/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO){
        userDTO = userService.registerUser(userDTO);
        return new ResponseEntity<String>(userDTO.getEmail() +" is Registered Successfull", HttpStatus.CREATED); //sonarLint Plug
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserDTO userDTO){
        boolean isLogin = userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
        if(isLogin){
            return new ResponseEntity<String>("Login Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Bhag BSDK", HttpStatus.FORBIDDEN);
        }
    }
}
