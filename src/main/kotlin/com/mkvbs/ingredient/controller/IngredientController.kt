package com.mkvbs.ingredient.controller

import com.mkvbs.ingredient.dto.IngredientDto
import com.mkvbs.ingredient.dto.IngredientResponseDto
import com.mkvbs.ingredient.service.IIngredientService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.UUID

@RestController
@RequestMapping("/api/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
class IngredientController(
    private val ingredientService: IIngredientService
){
    @PostMapping("addIngredient")
    fun addIngredient(@RequestBody ingredientDto: IngredientDto): ResponseEntity<IngredientResponseDto> {
        val savedIngredient = ingredientService.addIngredient(ingredientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIngredient)
    }

    @GetMapping("/ingredientById/{id}")
    fun getIngredientById(@PathVariable id: UUID): ResponseEntity<IngredientResponseDto> {
        val ingredient = ingredientService.getIngredientById(id)
        return ResponseEntity.status(HttpStatus.OK).body(ingredient)
    }

    @GetMapping("/ingredientByName/{name}")
    fun getIngredientByName(@PathVariable name: String): ResponseEntity<IngredientResponseDto> {
        val ingredient = ingredientService.getIngredientByName(name)
        return ResponseEntity.status(HttpStatus.OK).body(ingredient)
    }

    @PutMapping("/updateIngredient")
    fun updateIngredient(@RequestBody ingredientDto: IngredientDto): ResponseEntity<IngredientResponseDto> {
        val updatedIngredient = ingredientService.updateIngredient(ingredientDto)
        return ResponseEntity.status(HttpStatus.OK).body(updatedIngredient)
    }

    @DeleteMapping("/deleteIngredient/{id}")
    fun deleteIngredient(@PathVariable id: UUID): ResponseEntity<IngredientResponseDto> {
        val deletedIngredient = ingredientService.deleteIngredient(id)
        return ResponseEntity.status(HttpStatus.OK).body(deletedIngredient)
    }
}