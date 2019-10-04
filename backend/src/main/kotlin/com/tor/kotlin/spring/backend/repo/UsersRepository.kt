package com.tor.kotlin.spring.backend.repo

import com.tor.kotlin.spring.backend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.*
import javax.transaction.Transactional

interface UsersRepository : JpaRepository<User, Long> {
    fun existsByUsername(@Param("username") username: String): Boolean
    fun findByUsername(@Param("username") username: String): Optional<User>
    fun findByEmail(@Param("email") email: String): Optional<User>
    @Transactional
    fun deleteByUsername(@Param("username") username: String)
}