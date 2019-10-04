package com.tor.kotlin.spring.backend.repo

import com.tor.kotlin.spring.backend.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository:CrudRepository<Person,Long> {
}