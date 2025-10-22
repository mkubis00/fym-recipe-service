package com.mkvbs.recipe_service.exception

import com.mkvbs.recipe_service.dto.ErrorResponseDto
import com.mkvbs.recipe_service.exception.no_resource_found.NoResourceFoundException
import com.mkvbs.recipe_service.exception.id_null.ResourceIdNullException
import com.mkvbs.recipe_service.exception.resource_already_exists.ResourceAlreadyExistsException
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



    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleResourceAlreadyExists(ex: ResourceAlreadyExistsException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNotResourceExists(ex: NoResourceFoundException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.NOT_FOUND, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceIdNullException::class)
    fun handleResourceIdNullException(ex: ResourceIdNullException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}