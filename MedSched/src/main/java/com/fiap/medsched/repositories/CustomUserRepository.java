package com.fiap.medsched.repositories;

import com.fiap.medsched.dtos.CreateUpdateUserRequest;
import com.fiap.medsched.dtos.CreateUpdateUserResponse;
import com.fiap.medsched.models.UserModel;

import java.util.List;

public interface CustomUserRepository {
    CreateUpdateUserResponse createUser(UserModel userModel);
    CreateUpdateUserResponse updateUser(CreateUpdateUserRequest request);
    UserModel getUserById(Long id);
    List<UserModel> getAllUsers();
    void deleteUser(Long id);
}
