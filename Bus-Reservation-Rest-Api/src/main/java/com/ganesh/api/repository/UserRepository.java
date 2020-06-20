package com.ganesh.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
    User findOneByActivationHash(String activationHash);
}