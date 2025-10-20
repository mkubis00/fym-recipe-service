package com.mkvbs.ingredient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IngredientApplication

fun main(args: Array<String>) {
	runApplication<IngredientApplication>(*args)
}
