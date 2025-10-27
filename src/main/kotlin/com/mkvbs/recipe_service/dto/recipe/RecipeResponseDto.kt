package com.mkvbs.recipe_service.dto.recipe

import com.mkvbs.recipe_service.dto.ingredient.IngredientResponseDto
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(
    name= "Recipe Response",
    description = "Schema to hold recipe information"
)
data class RecipeResponseDto(

    @field:Schema(
        description = "UUID of the recipe",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val id: UUID,

    @field:Schema(
        description = "List of ingredients of recipe"
    )
    val ingredients: List<IngredientResponseDto>,

    @field:Schema(
        description = "Name of the recipe",
        example = "scramble egg"
    )
    val name: String,

    @field:Schema(
        description = "Description of the recipe",
        example = "Description of scramble egg"
    )
    val description: String,

    @field:Schema(
        description = "List of steps of recipe, recipe contains at least 3 steps.",
        example = "[\"step1\", \"step2\", \"step3\"]"
    )
    val steps: List<String>,
)