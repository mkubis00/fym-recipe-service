package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.exception.id_null.IngredientIdNullException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientFoundException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientsFoundException
import com.mkvbs.recipe_service.exception.resource_already_exists.IngredientAlreadyExistsException
import java.util.UUID

/**
 * Ingredient service
 *
 * @constructor Create empty ingredient service
 */
interface IIngredientService {

    /**
     * Add ingredient
     *
     * @param ingredientToSave
     * @return created ingredient
     * @throws IngredientAlreadyExistsException
     */
    fun addIngredient(ingredientToSave: Ingredient): Ingredient

    /**
     * Delete ingredient
     *
     * @param id
     * @return deleted ingredient
     * @throws NoIngredientFoundException
     */
    fun deleteIngredient(id: UUID): Ingredient

    /**
     * Update ingredient
     *
     * @param ingredientToUpdate
     * @return updated ingredient
     * @throws NoIngredientFoundException
     * @throws IngredientIdNullException
     */
    fun updateIngredient(ingredientToUpdate: Ingredient): Ingredient

    /**
     * Get ingredient by id
     *
     * @param id
     * @return ingredient with specific id
     * @throws NoIngredientFoundException
     */
    fun getIngredientById(id: UUID): Ingredient

    /**
     * Get ingredient by name
     *
     * @param name
     * @return ingredient with specific name
     * @throws NoIngredientFoundException
     */
    fun getIngredientByName(name: String): Ingredient

    /**
     * Get ingredients
     *
     * @param ingredientIds
     * @return list of ingredients with specific ids
     * @throws NoIngredientsFoundException
     */
    fun getIngredients(ingredientIds: List<UUID>): MutableList<Ingredient>
}