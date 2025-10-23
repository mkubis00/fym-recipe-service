package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.exception.no_resource_found.NoRecipeFoundException
import com.mkvbs.recipe_service.repository.RecipeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RecipeServiceImpl(
    private val recipeRepository: RecipeRepository,
    private val ingredientService: IIngredientService,
) : IRecipeService {
    override fun addRecipe(recipeToCreate: Recipe): Recipe {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun getRecipe(recipeId: UUID): Recipe {
        val recipeEntity =
            recipeRepository.findById(recipeId).orElseThrow { NoRecipeFoundException("ID", recipeId.toString()) }
//        val ingredients = ingredientService.getIngredients(recipeEntity.)
        TODO("Not yet implemented")
    }
}