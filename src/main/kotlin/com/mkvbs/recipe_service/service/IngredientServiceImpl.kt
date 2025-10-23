package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.exception.id_null.IngredientIdNullException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientFoundException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientsFoundException
import com.mkvbs.recipe_service.exception.resource_already_exists.IngredientAlreadyExistsException
import com.mkvbs.recipe_service.repository.IngredientRepository
import com.mkvbs.recipe_service.utlis.ingredient.toEntity
import com.mkvbs.recipe_service.utlis.ingredient.toDomain
import com.mkvbs.recipe_service.utlis.ingredient.toEntityWithId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class IngredientServiceImpl(
    private val ingredientRepository: IngredientRepository,
) : IIngredientService {

    override fun addIngredient(ingredientToSave: Ingredient): Ingredient {
        val isIngredientExists = this.ingredientRepository.existsByName(ingredientToSave.name)
        if (isIngredientExists) {
            throw IngredientAlreadyExistsException(ingredientToSave.name)
        }
        return ingredientRepository.save(ingredientToSave.toEntity()).toDomain()
    }

    override fun deleteIngredient(id: UUID): Ingredient {
        val ingredientToDelete = ingredientRepository.findById(id).orElseThrow { NoIngredientFoundException("ID", id.toString()) }
        ingredientRepository.delete(ingredientToDelete)
        return ingredientToDelete.toDomain()
    }

    override fun updateIngredient(ingredientToUpdate: Ingredient): Ingredient {
        if (ingredientToUpdate.id != null) {
            val isIngredientExists = ingredientRepository.existsById(ingredientToUpdate.id)
            if (isIngredientExists) {
                return ingredientRepository.save(ingredientToUpdate.toEntityWithId()).toDomain()
            } else {
                throw NoIngredientFoundException("ID", ingredientToUpdate.id.toString())
            }
        } else {
            throw IngredientIdNullException()
        }
    }

    override fun getIngredientById(id: UUID): Ingredient {
        val ingredient = ingredientRepository.findById(id).orElseThrow { NoIngredientFoundException("ID", id.toString()) }
        return ingredient.toDomain()
    }

    override fun getIngredientByName(name: String): Ingredient {
        val ingredientLowerCase = name.lowercase()
        val ingredient =
            ingredientRepository.findIngredientByName(ingredientLowerCase)
                .orElseThrow { NoIngredientFoundException("name", ingredientLowerCase) }
        return ingredient.toDomain()
    }

    @Transactional(readOnly = true)
    override fun getIngredients(ingredientIds: List<UUID>): List<Ingredient> {
        val existingIngredientsEntities = ingredientRepository.findAllById(ingredientIds)
        val existingIngredients = existingIngredientsEntities.map { it.toDomain() }
        val noFoundIngredients = ingredientIds.filter { id -> existingIngredients.any { it.id == id } }
        if (noFoundIngredients.isNotEmpty()) {
            val noFoundIngredientsIds = noFoundIngredients.joinToString(", ")
            throw NoIngredientsFoundException("ids", noFoundIngredientsIds)
        }
        return existingIngredients
    }
}