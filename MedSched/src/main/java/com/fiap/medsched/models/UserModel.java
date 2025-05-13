package com.fiap.medsched.models;

import com.fiap.medsched.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String name;
    private String surname;
    private String email;
    private UserType userType;
}
