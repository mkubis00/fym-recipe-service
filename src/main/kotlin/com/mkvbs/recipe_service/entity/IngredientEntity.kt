package com.mkvbs.recipe_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "ingredient")
data class IngredientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    val name: String,
)
