package com.mkvbs.recipe_service.utlis

import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.dto.recipe.RecipeResponseDto
import com.mkvbs.recipe_service.entity.RecipeEntity
import com.mkvbs.recipe_service.utlis.recipe.toDomain
import com.mkvbs.recipe_service.utlis.recipe.toResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

fun Page<RecipeEntity>.contentToDomain(): Page<Recipe> {
    val contentAsDomain = content.map { recipe -> recipe.toDomain() }
    return PageImpl(contentAsDomain, pageable, totalElements)
}

fun Page<Recipe>.contentToResponseDto(): Page<RecipeResponseDto> {
    val contentAsDto = content.map { recipe -> recipe.toResponseDto() }
    return PageImpl(contentAsDto, pageable, totalElements)
}