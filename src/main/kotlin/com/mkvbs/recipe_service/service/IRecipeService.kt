package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Recipe
import org.springframework.data.domain.Page
import java.util.*

/**
 * Recipe service
 *
 * @constructor Create empty recipe service
 */
interface IRecipeService {

    /**
     * Add recipe
     *
     * @param recipeToCreate
     * @param ingredientsIds
     * @return
     */
    fun addRecipe(recipeToCreate: Recipe, ingredientsIds: Set<UUID>): Recipe

    /**
     * Get recipe
     *
     * @param recipeId
     * @return
     */
    fun getRecipe(recipeId: UUID): Recipe

    /**
     * Update recipe
     *
     * @param recipeToUpdate
     * @param ingredientsIds
     * @return
     */
    fun updateRecipe(recipeToUpdate: Recipe, ingredientsIds: Set<UUID>): Recipe

    /**
     * Delete recipe
     *
     * @param recipeId
     * @return
     */
    fun deleteRecipe(recipeId: UUID): Recipe


    /**
     * Retrieves paginated recipes filtered by multiple optional criteria.
     *
     * @param page Index of the requested page (0-based).
     * @param pageSize Number of items per page.
     *
     * @param minIngredientCount Minimum number of ingredients a recipe must have.
     *        If `null`, the lower bound is ignored.
     * @param maxIngredientCount Maximum number of ingredients a recipe may have.
     *        If `null`, the upper bound is ignored.
     *
     * @param minStepsCount Minimum number of steps a recipe must contain.
     *        If `null`, the lower bound is ignored.
     * @param maxStepsCount Maximum number of steps a recipe may contain.
     *        If `null`, the upper bound is ignored.
     *
     * @param includedIngredientsIds Set of ingredient IDs that must be present in the recipe.
     *        If `null` or empty, this filter is skipped.
     * @param excludedIngredientsIds Set of ingredient IDs that must NOT appear in the recipe.
     *        If `null` or empty, this filter is skipped.
     *
     * @return A paginated list of recipes matching the provided filters.
     */
    fun getRecipes(
        page: Int,
        pageSize: Int,
        minIngredientCount: Int?,
        maxIngredientCount: Int?,
        minStepsCount: Int?,
        maxStepsCount: Int?,
        includedIngredientsIds: Set<UUID>?,
        excludedIngredientsIds: Set<UUID>?,
    ): Page<Recipe>
}