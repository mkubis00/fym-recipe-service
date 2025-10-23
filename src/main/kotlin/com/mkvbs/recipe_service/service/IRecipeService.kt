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
     * @return
     * @throws
     */
    fun addRecipe(recipeToCreate: Recipe): Recipe

    /**
     * Get recipe by id
     *
     * @param recipeId
     * @return Recipe objects
     * @throws
     */
    fun getRecipe(recipeId: UUID): Recipe
}