package com.tor.kotlin.spring.backend.jaas.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class LoginUser : Serializable {
    @JsonProperty("email")
    var username: String? = null

    @JsonProperty("password")
    var password: String? = null

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    constructor()

    companion object{
        private const val serialVersionUID = -1764970284520387975L
    }
}