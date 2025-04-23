package com.example.user.service.repository;

import com.example.user.service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmail(String email);
    Optional<Users> findByToken(String token);


}
