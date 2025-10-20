package com.mkvbs.ingredient.repository

import com.mkvbs.ingredient.entity.IngredientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface IngredientRepository : JpaRepository<IngredientEntity, UUID> {
    fun findIngredientByName(name: String): Optional<IngredientEntity>
    fun existsByName(name: String): Boolean
}