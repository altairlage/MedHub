package com.fiap.medsched.services;

import com.fiap.medsched.dtos.CreateUpdateUserRequest;
import com.fiap.medsched.dtos.CreateUpdateUserResponse;
import com.fiap.medsched.enums.UserType;
import com.fiap.medsched.models.UserModel;
import com.fiap.medsched.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public CreateUpdateUserResponse createUpdateUser(CreateUpdateUserRequest request) {
        if(request.getId() == null){
            //log: CREATE USER REQUEST
            UserModel userModel = new UserModel(request.getName(), request.getSurname(), UserType.valueOf(request.getUserType().toUpperCase()));
            return userRepository.createUser(userModel);
        } else{
            //log: UPDATE USER REQUEST
            return userRepository.updateUser(request);
        }
    }

    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }

    public UserModel getUserById(long id) {
        return userRepository.getUserById(id);
    }

    public List<UserModel> getAllUsersByUserType(String userType) {
        return userRepository.getAllByUserType(UserType.valueOf(userType.toUpperCase()));
    }

    public List<UserModel> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
