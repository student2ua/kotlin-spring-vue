package com.tor.kotlin.spring.backend.repo

import com.tor.kotlin.spring.backend.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(@Param("name") name: String): Role
}