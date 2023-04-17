package com.example.airbnbApi.category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

//    @EntityGraph(attributePaths = {"parent","children"})
    List<Category> findAll();

    Category findByName(String name);


}
