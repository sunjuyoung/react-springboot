package com.example.airbnbApi.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
