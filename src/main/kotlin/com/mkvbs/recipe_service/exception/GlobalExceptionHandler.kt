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

/**
 * Global exception handler
 *
 * @constructor Create empty Global exception handler
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    /**
     * Handle global exceptions
     *
     * @param ex cached Exception
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(Exception::class)
    fun handleGlobalExceptions(ex: Exception): ResponseEntity<ErrorResponseDto> {
        val errorResponse = if (ex.message != null) {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.message!!)
        } else {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error, ${ex::class.simpleName}")
        }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }


    /**
     * Handle resource already exists exception
     *
     * @param ex cached ResourceAlreadyExistsException
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleResourceAlreadyExistsException(ex: ResourceAlreadyExistsException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    /**
     * Handle no resource found exception
     *
     * @param ex cached NoResourceFoundException
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(ex: NoResourceFoundException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.NOT_FOUND, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    /**
     * Handle resource id null exception
     *
     * @param ex cached ResourceIdNullException
     * @return ResponseEntity<ErrorResponseDto>
     *///maybe internal error???
    @ExceptionHandler(ResourceIdNullException::class)
    fun handleResourceIdNullException(ex: ResourceIdNullException) : ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}