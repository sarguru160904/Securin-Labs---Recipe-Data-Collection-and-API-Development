package com.securin.recipe.controller;

import com.securin.recipe.model.Recipe;
import com.securin.recipe.repository.RecipeRepository;
import com.securin.recipe.specification.RecipeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*") // Allows your future frontend to talk to this backend
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public Page<Recipe> getRecipes(
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double minRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        // 1. Build dynamic filters
        Specification<Recipe> spec = Specification.where(RecipeSpecification.hasCuisine(cuisine))
                .and(RecipeSpecification.hasTitleLike(title))
                .and(RecipeSpecification.hasRatingGte(minRating));

        // 2. Handle Sorting and Pagination
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        // 3. Execute query
        return recipeRepository.findAll(spec, pageable);
    }
}
