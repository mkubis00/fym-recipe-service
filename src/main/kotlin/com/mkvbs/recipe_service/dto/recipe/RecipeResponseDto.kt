package com.mkvbs.recipe_service.dto.recipe

import com.mkvbs.recipe_service.dto.ingredient.IngredientResponseDto
import java.util.UUID

data class RecipeResponseDto(
    val id: UUID,
    val ingredients: List<IngredientResponseDto>,
    val name: String,
    val description: String,
    val steps: List<String>,
)