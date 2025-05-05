package com.fiap.medsched.controllers;

import com.fiap.medsched.dtos.CreateUpdateUserRequest;
import com.fiap.medsched.dtos.CreateUpdateUserResponse;
import com.fiap.medsched.models.UserModel;
import com.fiap.medsched.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    @MutationMapping
    public CreateUpdateUserResponse createUser(@RequestBody CreateUpdateUserRequest request){
        return userService.createUpdateUser(request);
    }

    @PostMapping("/update")
    @MutationMapping
    public CreateUpdateUserResponse updateUser(@RequestBody CreateUpdateUserRequest request){
        return userService.createUpdateUser(request);
    }

    @DeleteMapping("/{id}/delete")
    @MutationMapping
    public void deleteUser(@PathVariable long id){
         userService.deleteUser(id);
    }

    @GetMapping
    @QueryMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @QueryMapping
    public UserModel getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{userType}")
    @QueryMapping
    public List<UserModel> getAllUsersByUserType(@PathVariable String userType) {
        return userService.getAllUsersByUserType(userType);
    }

}
