package com.example.recipejson.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.recipejson.dto.RecipeDTO;
import com.example.recipejson.service.RecipeService;
import java.util.List;
import java.util.Map;

@Controller

public class RecipeController {
	public RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	@PostMapping("/recipes")	
	public ResponseEntity<List<RecipeDTO>> uploadRecipes(@RequestParam("file") MultipartFile file) {
		try {
			List<RecipeDTO> savedRecipes = recipeService.saveRecipes(file);
			return ResponseEntity.ok(savedRecipes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/recipes/top")
	public ResponseEntity<Map<String, List<RecipeDTO>>> getTopRecipes(@RequestParam(defaultValue = "5") int limit) {
		try {
			Map<String, List<RecipeDTO>> topRecipes = recipeService.getTopRecipes(limit);
			return ResponseEntity.ok(topRecipes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
