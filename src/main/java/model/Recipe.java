package com.securin.recipe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("Contient")
    private String contient;

    @JsonProperty("Country_State")
    private String countryState;

    private String cuisine;
    private String title;

    @JsonProperty("URL")
    @Column(length = 1000)
    private String url;

    private Double rating;

    @JsonProperty("total_time")
    private Integer totalTime;

    @JsonProperty("prep_time")
    private Integer prepTime;

    @JsonProperty("cook_time")
    private Integer cookTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient", columnDefinition = "TEXT")
    private List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "recipe_instructions", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "instruction", columnDefinition = "TEXT")
    private List<String> instructions;

    @Embedded
    private Nutrients nutrients;

    private String serves;
}