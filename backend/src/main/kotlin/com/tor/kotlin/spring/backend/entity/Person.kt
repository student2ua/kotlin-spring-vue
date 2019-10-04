package com.tor.kotlin.spring.backend.entity

import javax.persistence.*

@Entity
@Table(name = "person" ,schema = "fortest")
data class Person (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id:Long,
        @Column(nullable = false)
        val name:String
)