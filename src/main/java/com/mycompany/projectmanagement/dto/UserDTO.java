package com.mycompany.projectmanagement.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) //
public class UserDTO {

    @NotNull(message = "Owner Email is mandatory")
    @NotEmpty(message = "Owner Email cannot be empty")
    @Size(min=1, max=50, message = "Owner Email should be between 1 to 50 characteristics in length")
    private String Email;

    @Pattern(regexp = "[a-zA-Z]*")
    @NotNull(message = "Password can't be null")
    @NotEmpty(message = "Password can't be empty")
    private String password;
    private String firstName;
    private String lastName;
}
