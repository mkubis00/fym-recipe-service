package com.mkvbs.recipe_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.util.UUID

@Entity(name = "recipe")
data class RecipeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @ManyToMany
    @JoinTable(
        name = "recipe_ingredient",
        joinColumns = [JoinColumn(name = "recipe_id")],
        inverseJoinColumns = [JoinColumn(name = "ingredient_id")]
    )
    val ingredients: MutableList<IngredientEntity>,

    val name: String,
    val description: String,
    val steps: List<String>,
    val numberOfIngredients: Int,
)