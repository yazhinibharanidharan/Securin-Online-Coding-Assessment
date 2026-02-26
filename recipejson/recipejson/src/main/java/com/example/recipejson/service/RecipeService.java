package com.example.recipejson.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.recipejson.dto.RecipeDTO;
import com.example.recipejson.model.Recipe;
import com.example.recipejson.repository.RecipeRepository;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class RecipeService {
	public RecipeRepository recipeRepository;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final static int batchSize = 500;
	
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	public List<RecipeDTO> saveRecipes(MultipartFile file) throws Exception {
		Map<String, RecipeDTO> data = objectMapper.readValue(file.getInputStream(),new TypeReference<Map<String, RecipeDTO>>() {});
		
		List<Recipe> batch = new ArrayList<>();
		
		List<RecipeDTO> savedRecipesDTO = new ArrayList<>();
		
		for (RecipeDTO dto : data.values()) {
			Recipe recipe = new Recipe();
			recipe.setTitle(dto.getTitle());
			recipe.setCuisine(dto.getCuisine());
			recipe.setRating(dto.getRating());
			recipe.setPrepTime(dto.getPrepTime());
			recipe.setCookTime(dto.getCookTime());
			recipe.setTotalTime(dto.getTotalTime());
			recipe.setDescription(dto.getDescription());
			recipe.setServes(dto.getServes());
			recipe.setNutrients(objectMapper.writeValueAsString(dto.getNutrients()));

			batch.add(recipe);

			if (batch.size() == batchSize) {
				List<Recipe> savedRecipes = recipeRepository.saveAll(batch);
				for (Recipe savedRecipe : savedRecipes) {
					RecipeDTO savedDTO = new RecipeDTO();
					savedDTO.setId(savedRecipe.getId());
					savedDTO.setTitle(savedRecipe.getTitle());
					savedDTO.setCuisine(savedRecipe.getCuisine());
					savedDTO.setRating(savedRecipe.getRating());
					savedDTO.setPrepTime(savedRecipe.getPrepTime());
					savedDTO.setCookTime(savedRecipe.getCookTime());
					savedDTO.setTotalTime(savedRecipe.getTotalTime());
					savedDTO.setDescription(savedRecipe.getDescription());
					savedDTO.setServes(savedRecipe.getServes());
					savedDTO.setNutrients(objectMapper.readValue(savedRecipe.getNutrients(), new TypeReference<Map<String, String>>() {}));
					savedRecipesDTO.add(savedDTO);
				}
				batch.clear();
			}
		}

		if (!batch.isEmpty()) {
			List<Recipe> savedRecipes = recipeRepository.saveAll(batch);
			for (Recipe savedRecipe : savedRecipes) {
				RecipeDTO savedDTO = new RecipeDTO();
				savedDTO.setId(savedRecipe.getId());
				savedDTO.setTitle(savedRecipe.getTitle());
				savedDTO.setCuisine(savedRecipe.getCuisine());
				savedDTO.setRating(savedRecipe.getRating());
				savedDTO.setPrepTime(savedRecipe.getPrepTime());
				savedDTO.setCookTime(savedRecipe.getCookTime());
				savedDTO.setTotalTime(savedRecipe.getTotalTime());
				savedDTO.setDescription(savedRecipe.getDescription());
				savedDTO.setServes(savedRecipe.getServes());
				savedDTO.setNutrients(objectMapper.readValue(savedRecipe.getNutrients(), new TypeReference<Map<String, String>>() {}));
				savedRecipesDTO.add(savedDTO);
			}
		}

		return savedRecipesDTO;
	}
	
	public Map<String, List<RecipeDTO>> getTopRecipes(int limit) 
	{
	
	 List<RecipeDTO> recipes = recipeRepository.findAll(Sort.by(Sort.Direction.DESC, "rating")).stream().limit(limit)
			 
	 		.map(recipe -> {
	 			RecipeDTO dto = new RecipeDTO();
	 			dto.setId(recipe.getId());
	 			dto.setTitle(recipe.getTitle());
	 			dto.setCuisine(recipe.getCuisine());
	 			dto.setRating(recipe.getRating());
	 			dto.setPrepTime(recipe.getPrepTime());
	 			dto.setCookTime(recipe.getCookTime());
	 			dto.setTotalTime(recipe.getTotalTime());
	 			dto.setDescription(recipe.getDescription());
	 			dto.setNutrients(objectMapper.readValue(recipe.getNutrients(), new TypeReference<Map<String, String>>() {}));
	 			dto.setServes(recipe.getServes());
	 			return dto;
	 		})
	 		.toList();

	   return Map.of("data", recipes);
	}
}