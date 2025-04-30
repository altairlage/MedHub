package com.fiap.medsched.dtos;

import com.fiap.medsched.entities.Users;
import com.fiap.medsched.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUpdateUserResponse {
    private Long id;
    private String name;
    private String surname;
    private UserType userType;

    public CreateUpdateUserResponse(Users users) {
        this.setId(users.getId());
        this.setName(users.getName());
        this.setSurname(users.getSurname());
        this.setUserType(users.getUserType());
    }
}
