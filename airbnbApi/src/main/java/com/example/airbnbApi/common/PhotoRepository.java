package com.example.airbnbApi.common;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo,Long> {

    Optional<Photo> findByFileName(String fileName);

}
