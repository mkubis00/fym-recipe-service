package com.mkvbs.recipe_service.exception

import com.mkvbs.recipe_service.dto.ErrorResponseDto
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleGlobalExceptions(ex: Exception): ResponseEntity<ErrorResponseDto> {
        val errorResponse = if (ex.message != null) {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.message!!)
        } else {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error, ${ex::class.simpleName}")
        }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }



    @ExceptionHandler(IngredientAlreadyExistsException::class)
    fun handleIngredientAlreadyExists(ex: IngredientAlreadyExistsException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IngredientNotExistsException::class)
    fun handleIngredientNotExists(ex: IngredientNotExistsException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.NOT_FOUND, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceIdNullException::class)
    fun handleResourceIdNullException(ex: ResourceIdNullException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}