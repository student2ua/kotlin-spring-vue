package com.tor.kotlin.spring.backend.jaas

import com.tor.kotlin.spring.backend.repo.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var userRepository: UsersRepository

    @Throws(UsernameNotFoundException::class)      @Cacheable(cacheNames=["user"])
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username).get()
                ?: throw UsernameNotFoundException("User '$username' not found")

        val authorities: List<GrantedAuthority> = user.roles!!
                .stream()
                .map({ role -> SimpleGrantedAuthority(role.name) })
                .collect(Collectors.toList<GrantedAuthority>())

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.password)
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build()
    }

}