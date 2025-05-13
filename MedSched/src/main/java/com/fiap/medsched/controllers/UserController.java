package com.fiap.medsched.controllers;

import com.fiap.medsched.dtos.CreateUpdateUserRequest;
import com.fiap.medsched.dtos.CreateUpdateUserResponse;
import com.fiap.medsched.models.UserModel;
import com.fiap.medsched.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @MutationMapping
    public CreateUpdateUserResponse createUser(@Argument CreateUpdateUserRequest request){
        return userService.createUpdateUser(request);
    }

    @MutationMapping
    public CreateUpdateUserResponse updateUser(@Argument CreateUpdateUserRequest request){
        return userService.createUpdateUser(request);
    }

    @MutationMapping
    public Long deleteUser(@Argument Long userId){
        userService.deleteUser(userId);
        return userId;
    }

    @QueryMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public UserModel getUserById(@Argument Long userId) {
        return userService.getUserById(userId);
    }

    @QueryMapping
    public List<UserModel> getAllUsersByUserType(@Argument String userType) {
        return userService.getAllUsersByUserType(userType);
    }

}
