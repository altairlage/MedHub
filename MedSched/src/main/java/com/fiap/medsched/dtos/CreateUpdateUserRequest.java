package com.fiap.medsched.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateUserRequest {
    private Long id;
    private String name;
    private String surname;
    private String userType;
}
