package com.mkvbs.ingredient.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "ingredient")
data class IngredientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    final val id: UUID?,

    val name: String,
)
