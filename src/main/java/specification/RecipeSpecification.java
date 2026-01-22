package com.securin.recipe.specification;

import com.securin.recipe.model.Recipe;
import org.springframework.data.jpa.domain.Specification;

public class RecipeSpecification {

    public static Specification<Recipe> hasCuisine(String cuisine) {
        return (root, query, cb) -> cuisine == null ? null : cb.equal(root.get("cuisine"), cuisine);
    }

    public static Specification<Recipe> hasTitleLike(String title) {
        return (root, query, cb) -> title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Recipe> hasRatingGte(Double rating) {
        return (root, query, cb) -> rating == null ? null : cb.greaterThanOrEqualTo(root.get("rating"), rating);
    }
}