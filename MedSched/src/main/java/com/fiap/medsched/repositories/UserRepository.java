package com.fiap.medsched.repositories;

import com.fiap.medsched.entities.Users;
import com.fiap.medsched.enums.UserType;
import com.fiap.medsched.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long>, CustomUserRepository {
    List<UserModel> getAllByUserType(UserType userType);
}
