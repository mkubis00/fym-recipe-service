package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Recipe
import java.util.UUID

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
    fun addRecipe(recipeToCreate: Recipe, ingredientsIds: List<UUID>): Recipe

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
    fun updateRecipe(recipeToUpdate: Recipe, ingredientsIds: List<UUID>): Recipe

    /**
     * Delete recipe
     *
     * @param recipeId
     * @return
     */
    fun deleteRecipe(recipeId: UUID): Recipe
}