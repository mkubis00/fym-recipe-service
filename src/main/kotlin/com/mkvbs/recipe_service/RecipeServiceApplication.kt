package com.mkvbs.recipe_service

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
@OpenAPIDefinition(
    info = Info(
        title = "Recipe Service",
        version = "0.0.1",
        description = "Recipe Service API which provides management of recipes and ingredients",
        contact = Contact(
            name = "Maciej Kubis",
            url = "https://github.com/mkvbs/recipe_service",
            email = "maciej.k-2000@outlook.com"
        ),
        license = License(
            name = "Open Source Licenses",
        )
    )
)
class RecipeServiceApplication

fun main(args: Array<String>) {
	runApplication<RecipeServiceApplication>(*args)
}
