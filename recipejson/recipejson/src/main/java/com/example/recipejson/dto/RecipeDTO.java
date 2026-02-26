package com.example.recipejson.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeDTO {
	private Long id;
	private String title;
	private String cuisine;
	private Double rating;
	@JsonProperty("prep_time")
	private Integer prepTime;
	
	@JsonProperty("cook_time")
	private Integer cookTime;
	
	@JsonProperty("total_time")
	private Integer totalTime;
	
	public Integer getTotalTime() {
		return totalTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String description;
	private String serves;
	private Map<String,String> nutrients;

	public Map<String, String> getNutrients() {
		return nutrients;
	}

	public void setNutrients(Map<String, String> nutrients) {
		this.nutrients = nutrients;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getServes() {
		return serves;
	}

	public void setServes(String serves) {
		this.serves = serves;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desription) {
		this.description = desription;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	
}
