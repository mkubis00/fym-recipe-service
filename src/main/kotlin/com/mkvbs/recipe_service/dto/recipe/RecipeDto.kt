package com.mkvbs.recipe_service.dto.recipe

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.*

@Schema(
    name= "Recipe",
    description = "Schema to hold recipe information"
)
data class RecipeDto(

    @field:Schema(
        description = "UUID of the recipe, should not be included during creation of the recipe",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val id: UUID? = null,

    @field:Schema(
        description = "List of ingredients of recipe. Collection holds ingredients Ids. Recipe contains at least 3 ingredients.",
        example = "[\"3fa85f64-5717-4562-b3fc-2c963f66afa6\", \"1afa85f64-5717-4562-b3fc-2c963f66afa6\", \"4ca85f64-5717-4562-b3fc-2c963f66afa6\"]"
    )
    @field:Size(min = 3, message = "Recipe must contain at least 3 ingredients")
    val ingredients: Set<UUID>,

    @field:Schema(
        description = "Name of the recipe",
        example = "scramble egg"
    )
    @field:NotEmpty(message = "Name can not be null or empty")
    @field:Size(min = 3, max = 30, message = "Name can not be less than 3 and not greater than 30 characters")
    val name: String,

    @field:Schema(
        description = "Description of the recipe",
        example = "Description of scramble egg"
    )
    @field:NotEmpty(message = "Description can not be null or empty")
    @field:Size(min = 30, max = 150, message = "Description can not be less than 30 and not greater than 150 characters")
    val description: String,

    @field:Schema(
        description = "List of steps of recipe, recipe contains at least 3 steps.",
        example = "[\"step1\", \"step2\", \"step3\"]"
    )
    @field:Size(min = 3, message = "Recipe must contain at least 3 steps")
    val steps: Set<String>,
)