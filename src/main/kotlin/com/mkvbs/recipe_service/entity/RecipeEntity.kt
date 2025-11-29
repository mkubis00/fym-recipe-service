package com.mkvbs.recipe_service.entity

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.util.*

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
    val ingredients: Set<IngredientEntity>,

    val name: String,
    val description: String,
    @ElementCollection
    @CollectionTable(name = "recipe_steps", joinColumns = [JoinColumn(name = "recipe_id")])
    @Column(name = "step")
    val steps: Set<String>,
)