package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.entity.RecipeEntity
import com.mkvbs.recipe_service.exception.id_null.RecipeIdNullException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientsFoundForRecipeException
import com.mkvbs.recipe_service.exception.no_resource_found.NoRecipeFoundException

import com.mkvbs.recipe_service.repository.RecipeRepository
import com.mkvbs.recipe_service.utlis.recipe.toDomain
import com.mkvbs.recipe_service.utlis.recipe.toEntity
import com.mkvbs.recipe_service.utlis.recipe.toEntityWithId
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RecipeServiceImpl(
    private val recipeRepository: RecipeRepository,
    private val ingredientService: IIngredientService,
) : IRecipeService {
    override fun addRecipe(recipeToCreate: Recipe, ingredientsIds: List<UUID>): Recipe {
        recipeToCreate.ingredients = getIngredients(ingredientsIds)
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
    override fun updateRecipe(recipeToUpdate: Recipe, ingredientsIds: List<UUID>): Recipe {
        if (recipeToUpdate.id == null) throw RecipeIdNullException()
        val isRecipeExists = recipeRepository.existsById(recipeToUpdate.id)
        recipeToUpdate.ingredients = getIngredients(ingredientsIds)
        if (isRecipeExists) return recipeRepository.save(recipeToUpdate.toEntityWithId()).toDomain()
        throw NoRecipeFoundException("ID", recipeToUpdate.id.toString())
    }

    @Transactional
    override fun deleteRecipe(recipeId: UUID): Recipe {
        val recipeToDelete = recipeRepository.findById(recipeId).orElseThrow { NoRecipeFoundException("ID", recipeId.toString()) }
        recipeRepository.delete(recipeToDelete)
        return recipeToDelete.toDomain()
    }

    private fun getIngredients(ingredientIds: List<UUID>): MutableList<Ingredient> {
        return ingredientService.getIngredients(ingredientIds) // checks if ingredients exists, if not throws ex
    }
}