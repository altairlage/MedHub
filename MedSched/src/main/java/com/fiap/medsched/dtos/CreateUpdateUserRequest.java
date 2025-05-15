package com.fiap.medsched.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SchemaMapping("CreateUpdateUserRequest")
public class CreateUpdateUserRequest {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String userType;
}
