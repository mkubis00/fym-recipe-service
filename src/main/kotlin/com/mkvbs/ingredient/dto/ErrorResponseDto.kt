package com.mkvbs.ingredient.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponseDto(
    val errorCode: HttpStatus,
    val errorMessage: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)