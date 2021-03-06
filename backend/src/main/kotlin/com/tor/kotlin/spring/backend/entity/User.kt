package com.tor.kotlin.spring.backend.entity

import javax.persistence.*

@Entity
@Table(name = "users" ,schema = "fortest")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = 0,
        @Column(name = "username")
        var username: String? = null,
        @Column(name = "first_name")
        val firstName: String? = null,
        @Column(name = "last_name")
        val lastName: String? = null,
        @Column(name = "email")
        val email: String? = null,
        @Column(name = "password")
        val password: String? = null,
        @Column(name = "enabled")
        val enabled:Boolean=false,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "users_roles", schema = "fortest",
                joinColumns = [JoinColumn(name = "user_id",referencedColumnName ="id" )],
                inverseJoinColumns = [JoinColumn(name = "role_id",referencedColumnName = "id")])
        var roles:Collection<Role>?=null

)