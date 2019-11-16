package com.tor.kotlin.spring.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
//@EnableJdbcHttpSession
class BackendApplication

    fun main(args: Array<String>) {
        runApplication<BackendApplication>(*args)
    }
