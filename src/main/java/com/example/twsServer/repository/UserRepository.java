package com.example.twsServer.repository;

import com.example.twsServer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUserId(String userId);

    UserEntity findByUserId(String userId);

    UserEntity findByEmail(String email);
}
