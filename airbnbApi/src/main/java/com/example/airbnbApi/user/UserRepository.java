package com.example.airbnbApi.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<Account,Integer>,UserRepositoryExtension{

    @EntityGraph(attributePaths = {"favorites"})
    Optional<Account> findByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"favorites"})
    Account findFavoritesById(Integer id);
}
