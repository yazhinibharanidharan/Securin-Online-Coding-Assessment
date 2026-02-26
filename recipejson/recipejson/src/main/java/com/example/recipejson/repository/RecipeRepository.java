package com.example.recipejson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipejson.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
