package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.exception.id_null.IngredientIdNullException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientFoundException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientsFoundException
import com.mkvbs.recipe_service.exception.resource_already_exists.IngredientAlreadyExistsException
import com.mkvbs.recipe_service.exception.unable_to_delete.UnableToDeleteIngredientException
import com.mkvbs.recipe_service.repository.IngredientRepository
import com.mkvbs.recipe_service.utlis.ingredient.toDomain
import com.mkvbs.recipe_service.utlis.ingredient.toEntity
import com.mkvbs.recipe_service.utlis.ingredient.toEntityWithId
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class IngredientServiceImpl(
    private val ingredientRepository: IngredientRepository,
) : IIngredientService {

    @Transactional
    override fun addIngredient(ingredientToSave: Ingredient): Ingredient {
        val isIngredientExists = this.ingredientRepository.existsByName(ingredientToSave.name)
        if (isIngredientExists) {
            throw IngredientAlreadyExistsException(ingredientToSave.name)
        }
        return ingredientRepository.save(ingredientToSave.toEntity()).toDomain()
    }

    @Transactional
    override fun deleteIngredient(id: UUID): Ingredient {
        val ingredientToDelete = ingredientRepository.findById(id).orElseThrow { NoIngredientFoundException("ID", id.toString()) }
        try {
            ingredientRepository.delete(ingredientToDelete)
            ingredientRepository.flush()
        } catch (_: DataIntegrityViolationException) {
            throw UnableToDeleteIngredientException(id)
        }
        return ingredientToDelete.toDomain()
    }

    @Transactional
    override fun updateIngredient(ingredientToUpdate: Ingredient): Ingredient {
        if (ingredientToUpdate.id == null) throw IngredientIdNullException()
        val isIngredientExists = ingredientRepository.existsById(ingredientToUpdate.id)
        if (isIngredientExists) return ingredientRepository.save(ingredientToUpdate.toEntityWithId()).toDomain()
        throw NoIngredientFoundException("ID", ingredientToUpdate.id.toString())
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
    override fun getIngredients(ingredientIds: Set<UUID>): Set<Ingredient> {
        val existingIngredientsEntities = ingredientRepository.findAllById(ingredientIds)
        val existingIngredients = existingIngredientsEntities.map { it.toDomain() }
        val noFoundIngredients = ingredientIds.filter { id -> existingIngredients.none { it.id == id } }
        if (noFoundIngredients.isNotEmpty()) {
            val noFoundIngredientsIds = noFoundIngredients.joinToString(", ")
            throw NoIngredientsFoundException("ids", noFoundIngredientsIds)
        }
        return existingIngredients.toSet()
    }
}