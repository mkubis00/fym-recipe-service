package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.exception.ResourceAlreadyExistsException
import com.mkvbs.recipe_service.exception.NoResourceExistsException
import com.mkvbs.recipe_service.exception.ResourceIdNullException
import com.mkvbs.recipe_service.repository.IngredientRepository
import com.mkvbs.recipe_service.utlis.toEntity
import com.mkvbs.recipe_service.utlis.toDomain
import com.mkvbs.recipe_service.utlis.toEntityWithId
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class IngredientServiceImpl(
    private val ingredientRepository: IngredientRepository,
) : IIngredientService {

    override fun addIngredient(ingredientToSave: Ingredient): Ingredient {
        val isIngredientExists = this.ingredientRepository.existsByName(ingredientToSave.name)
        if (!isIngredientExists) {
            return ingredientRepository.save(ingredientToSave.toEntity()).toDomain()
        }
        throw ResourceAlreadyExistsException(ingredientToSave.name)
    }

    override fun getIngredientById(id: UUID): Ingredient {
        val ingredient = ingredientRepository.findById(id).orElseThrow { NoResourceExistsException(id) }
        return ingredient.toDomain()
    }

    override fun getIngredientByName(name: String): Ingredient{
        val ingredientLowerCase = name.lowercase()
        val ingredient =
            ingredientRepository.findIngredientByName(ingredientLowerCase).orElseThrow { NoResourceExistsException(ingredientLowerCase) }
        return ingredient.toDomain()
    }

    override fun deleteIngredient(id: UUID): Ingredient {
        val ingredientToDelete = ingredientRepository.findById(id).orElseThrow { NoResourceExistsException(id) }
        ingredientRepository.delete(ingredientToDelete)
        return ingredientToDelete.toDomain()
    }

    override fun updateIngredient(ingredientToUpdate: Ingredient): Ingredient {
        if (ingredientToUpdate.id != null) {
            val isIngredientExists = ingredientRepository.existsById(ingredientToUpdate.id)
            if (isIngredientExists) {
                return ingredientRepository.save(ingredientToUpdate.toEntityWithId()).toDomain()
            } else {
                throw ResourceAlreadyExistsException(ingredientToUpdate.id)
            }
        } else {
            throw ResourceIdNullException()
        }
    }
}