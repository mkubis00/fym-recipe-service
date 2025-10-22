package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.repository.RecipeRepository
import org.springframework.stereotype.Service

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val ingredientService: IIngredientService,
) : IRecipeService {
}