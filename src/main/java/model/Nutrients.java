package com.securin.recipe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Nutrients {
    private String calories;

    @JsonProperty("carbohydrateContent")
    private String carbohydrateContent;

    @JsonProperty("cholesterolContent")
    private String cholesterolContent;

    @JsonProperty("fiberContent")
    private String fiberContent;

    @JsonProperty("proteinContent")
    private String proteinContent;

    @JsonProperty("saturatedFatContent")
    private String saturatedFatContent;

    @JsonProperty("sodiumContent")
    private String sodiumContent;

    @JsonProperty("sugarContent")
    private String sugarContent;

    @JsonProperty("fatContent")
    private String fatContent;

    @JsonProperty("unsaturatedFatContent")
    private String unsaturatedFatContent;
}