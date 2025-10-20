package com.mkvbs.ingredient.service

import com.mkvbs.ingredient.dto.IngredientDto
import com.mkvbs.ingredient.dto.IngredientResponseDto
import com.mkvbs.ingredient.exception.IngredientAlreadyExistsException
import com.mkvbs.ingredient.exception.IngredientNotExistsException
import com.mkvbs.ingredient.exception.ResourceIdNullException
import com.mkvbs.ingredient.repository.IngredientRepository
import com.mkvbs.ingredient.utlis.toEntity
import com.mkvbs.ingredient.utlis.toEntityWithId
import com.mkvbs.ingredient.utlis.toResponseDto
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class IngredientServiceImpl(
    private val ingredientRepository: IngredientRepository,
) : IIngredientService {

    override fun addIngredient(ingredientDto: IngredientDto): IngredientResponseDto {
        val ingredientToSave = ingredientDto.toEntity()
        val isIngredientExists = this.ingredientRepository.existsByName(ingredientToSave.name)
        if (!isIngredientExists) {
            return ingredientRepository.save(ingredientToSave).toResponseDto()
        }
        throw IngredientAlreadyExistsException(ingredientToSave.name)
    }

    override fun getIngredientById(id: UUID): IngredientResponseDto {
        val ingredient = ingredientRepository.findById(id).orElseThrow { IngredientNotExistsException(id) }
        return ingredient.toResponseDto()
    }

    override fun getIngredientByName(name: String): IngredientResponseDto {
        val ingredientLowerCase = name.lowercase()
        val ingredient =
            ingredientRepository.findIngredientByName(ingredientLowerCase).orElseThrow { IngredientNotExistsException(ingredientLowerCase) }
        return ingredient.toResponseDto()
    }

    override fun deleteIngredient(id: UUID): IngredientResponseDto {
        val ingredientToDelete = ingredientRepository.findById(id).orElseThrow { IngredientNotExistsException(id) }
        ingredientRepository.delete(ingredientToDelete)
        return ingredientToDelete.toResponseDto()
    }

    override fun updateIngredient(ingredientDto: IngredientDto): IngredientResponseDto {
        val ingredientToUpdate = ingredientDto.toEntityWithId()
        if (ingredientToUpdate.id != null) {
            val isIngredientExists = ingredientRepository.existsById(ingredientToUpdate.id)
            if (isIngredientExists) {
                return ingredientRepository.save(ingredientToUpdate).toResponseDto()
            } else {
                throw IngredientAlreadyExistsException(ingredientToUpdate.id)
            }
        } else {
            throw ResourceIdNullException()
        }
    }
}