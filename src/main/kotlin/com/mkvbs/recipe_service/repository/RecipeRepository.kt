package com.mkvbs.recipe_service.repository

import com.mkvbs.recipe_service.entity.RecipeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RecipeRepository : JpaRepository<RecipeEntity, UUID> {
}