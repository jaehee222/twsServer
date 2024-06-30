package com.example.twsServer.repository;

import com.example.twsServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserId(String id);

    boolean existsByUserId(String id);

    User findByEmail(String email);
}
