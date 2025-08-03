package com.tor.kotlin.spring.backend.controllers

import com.tor.kotlin.spring.backend.entity.User
import com.tor.kotlin.spring.backend.jaas.JwtProvider
import com.tor.kotlin.spring.backend.jaas.JwtResponse
import com.tor.kotlin.spring.backend.jaas.ResponseMessage
import com.tor.kotlin.spring.backend.jaas.model.LoginUser
import com.tor.kotlin.spring.backend.jaas.model.NewUser
import com.tor.kotlin.spring.backend.repo.RoleRepository
import com.tor.kotlin.spring.backend.repo.UsersRepository
import io.swagger.annotations.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
@Api(value = "/api/auth", description = "Rest API for authentication operations", tags = arrayOf("Auth API"))
class AuthController() {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var userRepository: UsersRepository
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var encoder: PasswordEncoder
    @Autowired
    lateinit var jwtProvider: JwtProvider

    @ApiParam(required = true)
    @ApiOperation(value = "authenticate", response = JwtResponse::class)
    @ApiResponses(value = arrayOf(
        ApiResponse(code = 200, message = "authenticate", response = JwtResponse::class),
        ApiResponse(code = 401, message = "Неправильний логін або пароль")
    ))
    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginUser): ResponseEntity<*> {
        return try {
            val authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
            )

            SecurityContextHolder.getContext().authentication = authentication
            val user = userRepository.findByUsername(loginRequest.username!!).get()
            val jwt = jwtProvider.generateJwtToken(user.username!!)

            val authorities = user.roles!!.map { SimpleGrantedAuthority(it.name) }
            ResponseEntity.ok(JwtResponse(jwt, user.username, authorities))

        } catch (ex: AuthenticationException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseMessage("Неправильний логін або пароль"))
        }
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody newUser: NewUser): ResponseEntity<*> {
        val userCandidate: Optional<User> = userRepository.findByUsername(newUser.username!!)
        if (!userCandidate.isPresent) {
            if (usernameExists(newUser.username!!)) {
                return ResponseEntity(ResponseMessage("Username is already taken"), HttpStatus.BAD_REQUEST)
            } else if (emailExists(newUser.email!!)) {
                return ResponseEntity(ResponseMessage("Email is already in use"), HttpStatus.BAD_REQUEST)
            }
            val user = User(
                    0,
                    newUser.username!!,
                    newUser.firstName!!,
                    newUser.lastName!!,
                    newUser.email!!,
                    encoder.encode(newUser.password),
                    true
            )
            user.roles = Arrays.asList(roleRepository.findByName("ROLE_USER"))
            userRepository.save(user)
            return ResponseEntity(ResponseMessage("User registred - ok"), HttpStatus.OK)
        } else {
            return ResponseEntity(ResponseMessage("User alredy exists"), HttpStatus.BAD_REQUEST)
        }
    }
    @PostMapping("/refresh")
    fun refreshToken(authentication: Authentication): ResponseEntity<*> {

        val userCandidate: Optional<User> = userRepository.findByUsername(authentication.name)
        if (userCandidate.isPresent) {
            val user: User = userCandidate.get()
            logger.debug("user" + user!!.toString())
//            val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
//            SecurityContextHolder.getContext().setAuthentication(authentication)
            val jwt: String = jwtProvider.generateJwtToken(user.username!!)
            logger.debug("jwt: " + jwt)
            val authorities: List<GrantedAuthority> = user.roles!!
                    .stream()
                    .map({ role -> SimpleGrantedAuthority(role.name) })
                    .collect(Collectors.toList<GrantedAuthority>())
            logger.debug(authentication.authorities.toString())
            return ResponseEntity.ok(JwtResponse(jwt, user.username, authorities))
        } else {
            return ResponseEntity(ResponseMessage("User not found!"), HttpStatus.BAD_REQUEST)
        }
    }

    private fun emailExists(email: String): Boolean {
        return userRepository.findByEmail(email).isPresent //todo ????

    }

    //todo fun existsByUsername(@Param("username") username: String): Boolean ??
    private fun usernameExists(username: String): Boolean = userRepository.findByUsername(username).isPresent

    companion object {
        private val logger = LoggerFactory.getLogger(AuthController::class.java)
    }
}