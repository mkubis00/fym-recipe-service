package com.mkvbs.recipe_service.repository

import com.mkvbs.recipe_service.entity.RecipeEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RecipeRepository : JpaRepository<RecipeEntity, UUID>, JpaSpecificationExecutor<RecipeEntity> {

    @Query(
        value = """
    SELECT *
    FROM recipe
    WHERE id IN (
     SELECT recipe_id
    FROM recipe_ingredient
    GROUP BY recipe_id
    HAVING 
        (:minIngredientCount IS NULL OR COUNT(*) >= :minIngredientCount)
    AND (:maxIngredientCount IS NULL OR COUNT(*) <= :maxIngredientCount)
) AND id IN (
    SELECT recipe_id
    FROM recipe_steps
    GROUP BY recipe_id
    HAVING 
        (:minStepsCount IS NULL OR COUNT(*) >= :minStepsCount)
    AND (:maxStepsCount IS NULL OR COUNT(*) <= :maxStepsCount)
) AND id NOT IN (
    SELECT recipe_id
    FROM recipe_ingredient
    WHERE ingredient_id IN :excludedIngredientsIds
)
    """, nativeQuery = true
    )
    fun searchRecipesWithoutIncludedIngredients(
        pageable: Pageable,
        minIngredientCount: Int?,
        maxIngredientCount: Int?,
        minStepsCount: Int?,
        maxStepsCount: Int?,
        excludedIngredientsIds: Set<UUID>,
    ): Page<RecipeEntity>

    @Query(
        value = """
    SELECT *
    FROM recipe
    WHERE id IN (
     SELECT recipe_id
    FROM recipe_ingredient
    GROUP BY recipe_id
    HAVING 
        (:minIngredientCount IS NULL OR COUNT(*) >= :minIngredientCount)
    AND (:maxIngredientCount IS NULL OR COUNT(*) <= :maxIngredientCount)
) AND id IN (
    SELECT recipe_id
    FROM recipe_steps
    GROUP BY recipe_id
    HAVING 
        (:minStepsCount IS NULL OR COUNT(*) >= :minStepsCount)
    AND (:maxStepsCount IS NULL OR COUNT(*) <= :maxStepsCount)
) AND id NOT IN (
    SELECT recipe_id
    FROM recipe_ingredient
    WHERE ingredient_id IN :excludedIngredientsIds
) AND id IN (
    SELECT recipe_id
    FROM recipe_ingredient
    WHERE ingredient_id IN :includedIngredientsIds
)
    """, nativeQuery = true
    )
    fun searchRecipesWithIncludedIngredients(
        pageable: Pageable,
        minIngredientCount: Int?,
        maxIngredientCount: Int?,
        minStepsCount: Int?,
        maxStepsCount: Int?,
        excludedIngredientsIds: Set<UUID>,
        includedIngredientsIds: Set<UUID>,
    ): Page<RecipeEntity>
}