package com.tor.kotlin.spring.backend.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.annotations.ApiIgnore

/**
 * User: tor
 * Date: 01.08.2025
 * Time: 17:39
 * чтобы Spring Boot всегда возвращал index.html для всех маршрутов, которые не обрабатываются клиентским vue-router
 * то срабатыват то нет
 *
 */
@ApiIgnore
@Controller
class FrontendController {

    // Обработка всех путей кроме API и статики
    @RequestMapping(
            value = ["/{path:[^\\.]*}", "/{path:^(?!api|static|openAPI).*}/**/{subpath:[^\\.]*}"],
            method = [RequestMethod.GET]
    )
    fun forward(): String {
        return "forward:/index.html"
    }
}