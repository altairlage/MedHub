package com.fiap.medsched.repositories.implementations;

import com.fiap.medsched.dtos.CreateUpdateUserRequest;
import com.fiap.medsched.dtos.CreateUpdateUserResponse;
import com.fiap.medsched.entities.Users;
import com.fiap.medsched.enums.UserType;
import com.fiap.medsched.exceptions.MedException;
import com.fiap.medsched.models.UserModel;
import com.fiap.medsched.repositories.CustomUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public CreateUpdateUserResponse createUser(UserModel userModel) {
        Users user = new Users(userModel.getName(), userModel.getSurname(), userModel.getEmail(), userModel.getUserType());

        entityManager.persist(user);

        return new CreateUpdateUserResponse(user);
    }

    @Override
    @Transactional
    public CreateUpdateUserResponse updateUser(CreateUpdateUserRequest request) {
        Users user = entityManager.find(Users.class, request.getId());

        if (user == null) {
            throw new MedException("User not found");
        }

        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setUserType(UserType.valueOf(request.getUserType()));

        entityManager.merge(user);

        return new CreateUpdateUserResponse(user);
    }

    @Override
    @Transactional
    public UserModel getUserById(Long id) {
        Users user = entityManager.find(Users.class, id);

        if (user == null) {
            throw new MedException("User not found");
        }

        return new UserModel(user);
    }

    @Override
    @Transactional
    public List<UserModel> getAllUsers() {
        List<Users> userList = entityManager.createQuery("select u from Users u", Users.class).getResultList();
        List<UserModel> userModelList = new ArrayList<>();

        for (Users user : userList) {
            userModelList.add(new UserModel(user));
        }

        return userModelList;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Users user = entityManager.find(Users.class, id);

        if (user == null) {
            throw new MedException("User not found");
        }

        entityManager.remove(user);
    }
}
