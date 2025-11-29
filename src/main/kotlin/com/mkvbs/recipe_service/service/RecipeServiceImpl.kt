package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.entity.RecipeEntity
import com.mkvbs.recipe_service.exception.id_null.RecipeIdNullException
import com.mkvbs.recipe_service.exception.no_resource_found.NoRecipeFoundException
import com.mkvbs.recipe_service.repository.RecipeRepository
import com.mkvbs.recipe_service.utlis.contentToDomain
import com.mkvbs.recipe_service.utlis.recipe.toDomain
import com.mkvbs.recipe_service.utlis.recipe.toEntity
import com.mkvbs.recipe_service.utlis.recipe.toEntityWithId
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RecipeServiceImpl(
    private val recipeRepository: RecipeRepository,
    private val ingredientService: IIngredientService,
) : IRecipeService {
    override fun addRecipe(recipeToCreate: Recipe, ingredientsIds: Set<UUID>): Recipe {
        recipeToCreate.ingredients = getIngredients(ingredientsIds)
        val savedRecipeEntity = recipeRepository.save(recipeToCreate.toEntity())
        return savedRecipeEntity.toDomain()
    }

    @Transactional
    override fun getRecipe(recipeId: UUID): Recipe {
        val recipeEntity =
            recipeRepository.findById(recipeId).orElseThrow { NoRecipeFoundException("ID", recipeId.toString()) }
            return recipeEntity.toDomain()

    }

    @Transactional
    override fun updateRecipe(recipeToUpdate: Recipe, ingredientsIds: Set<UUID>): Recipe {
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

    override fun getRecipes(
        page: Int,
        pageSize: Int,
        minIngredientCount: Int?,
        maxIngredientCount: Int?,
        minStepsCount: Int?,
        maxStepsCount: Int?,
        includedIngredientsIds: Set<UUID>?,
        excludedIngredientsIds: Set<UUID>?,
    ): Page<Recipe> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        val setOfExcludedIngredientFroQuery = excludedIngredientsIds ?: emptySet()
        var pageOfFoundRecipesEntities: Page<RecipeEntity>;
        if (includedIngredientsIds == null || includedIngredientsIds.isEmpty()) {
            pageOfFoundRecipesEntities = recipeRepository.searchRecipesWithoutIncludedIngredients(
                pageable,
                minIngredientCount,
                maxIngredientCount,
                minStepsCount,
                maxStepsCount,
                setOfExcludedIngredientFroQuery
            )
        } else {
            pageOfFoundRecipesEntities = recipeRepository.searchRecipesWithIncludedIngredients(
                pageable,
                minIngredientCount,
                maxIngredientCount,
                minStepsCount,
                maxStepsCount,
                setOfExcludedIngredientFroQuery,
                includedIngredientsIds
            )
        }
        return pageOfFoundRecipesEntities.contentToDomain()
    }

    private fun getIngredients(ingredientIds: Set<UUID>): Set<Ingredient> {
        return ingredientService.getIngredients(ingredientIds) // checks if ingredients exists, if not throws ex
    }
}