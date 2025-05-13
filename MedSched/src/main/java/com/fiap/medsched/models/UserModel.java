package com.fiap.medsched.models;

import com.fiap.medsched.dtos.CreateUpdateUserRequest;
import com.fiap.medsched.entities.Users;
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
    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserType userType;

    public UserModel(CreateUpdateUserRequest request) {
        if(request.getId() != null){
            this.id = request.getId();
        }
        this.name = request.getName();
        this.surname = request.getSurname();
        this.email = request.getEmail();
        this.userType = UserType.valueOf(request.getUserType().toUpperCase());
    }

    public UserModel(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.userType = user.getUserType();
    }
}