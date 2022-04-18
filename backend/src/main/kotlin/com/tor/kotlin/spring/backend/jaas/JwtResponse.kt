package com.tor.kotlin.spring.backend.jaas

import io.swagger.annotations.ApiModelProperty
import org.springframework.security.core.GrantedAuthority

class JwtResponse(
        var accessToken: String?,
        @ApiModelProperty(notes = "Provided user name", required = true)
        var username: String?,
        val authorities: Collection<GrantedAuthority>) {
    var type = "Bearer"
}