package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientsFoundForRecipeException
import com.mkvbs.recipe_service.exception.no_resource_found.NoRecipeFoundException
import com.mkvbs.recipe_service.repository.RecipeRepository
import com.mkvbs.recipe_service.utlis.recipe.toDomain
import com.mkvbs.recipe_service.utlis.recipe.toEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RecipeServiceImpl(
    private val recipeRepository: RecipeRepository,
    private val ingredientService: IIngredientService,
) : IRecipeService {
    override fun addRecipe(recipeToCreate: Recipe, ingredientsIds: List<UUID>): Recipe {
        recipeToCreate.ingredients = ingredientService.getIngredients(ingredientsIds) // checks if ingredients exists, if not throws ex
        val savedRecipeEntity = recipeRepository.save(recipeToCreate.toEntity())
        return savedRecipeEntity.toDomain()
    }

    @Transactional
    override fun getRecipe(recipeId: UUID): Recipe {
        val recipeEntity =
            recipeRepository.findById(recipeId).orElseThrow { NoRecipeFoundException("ID", recipeId.toString()) }
        if (recipeEntity.numberOfIngredients == recipeEntity.ingredients.size) {
            return recipeEntity.toDomain()
        }
        throw NoIngredientsFoundForRecipeException(recipeId.toString())
    }

    @Transactional
    override fun updateRecipe(recipeToUpdate: Recipe): Recipe {
        TODO("Not yet implemented")
    }

    override fun deleteRecipe(recipeId: UUID): Recipe {
        TODO("Not yet implemented")
    }
}