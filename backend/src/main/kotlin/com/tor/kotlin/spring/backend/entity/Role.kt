package com.tor.kotlin.spring.backend.entity

import javax.persistence.*

@Entity
@Table(name = "roles" ,schema = "fortest")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @Column(name = "name")
        val name: String
)