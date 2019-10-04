package com.tor.kotlin.spring.backend.controllers

import com.tor.kotlin.spring.backend.jaas.JwtAuthEntryPoint
import com.tor.kotlin.spring.backend.repo.PersonRepository
import com.tor.kotlin.spring.backend.repo.UsersRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/api")
class BackendController() {
    @Autowired
    lateinit var personRepository: PersonRepository
    @Autowired
    lateinit var usersRepository: UsersRepository

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/persons")
    fun getPersons() = personRepository.findAll()

    @GetMapping("/usercontent")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun getUserContent(authentication: Authentication): String {
        logger.debug(authentication.toString())
        val user = usersRepository.findByUsername(authentication.name).get()
        logger.debug(user.roles.toString())
        return """hello ${user.firstName} ${user.lastName}!"""
    }

    @GetMapping("/admincontent")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    fun getAdminContent(): String = "Admin`s content"
    companion object {
        private val logger = LoggerFactory.getLogger(JwtAuthEntryPoint::class.java)
    }
}

data class Greeting(val id: Long, val content: String)
