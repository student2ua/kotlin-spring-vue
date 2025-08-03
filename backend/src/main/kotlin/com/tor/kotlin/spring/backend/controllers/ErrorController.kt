package com.tor.kotlin.spring.backend.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

/**
 * User: tor
 * Date: 31.07.2025
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
@Controller
class ErrorController : org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): ResponseEntity<String> {
        val statusCode = request.getAttribute("javax.servlet.error.status_code") as? Int
        val message = when (statusCode) {
            HttpStatus.NOT_FOUND.value() -> "404 — Сторінку не знайдено"
            HttpStatus.FORBIDDEN.value() -> "403 — Доступ заборонено"
            HttpStatus.INTERNAL_SERVER_ERROR.value() -> "500 — Помилка сервера"
            HttpStatus.METHOD_NOT_ALLOWED.value() -> "405 — Метод не дозволений"
            else -> "Невідома помилка, перейдіть на головну"
        }

        return ResponseEntity.status(statusCode ?: 500).body(message)
    }

    override fun getErrorPath(): String {
        return "/error"
    }
}
