package com.tor.kotlin.spring.backend

import com.tor.kotlin.spring.backend.jaas.JwtAuthEntryPoint
import com.tor.kotlin.spring.backend.jaas.JwtAuthTokenFilter
import com.tor.kotlin.spring.backend.jaas.UserDetailsServiceImpl
import com.tor.kotlin.spring.backend.repo.MarkRESTRepository
import com.tor.kotlin.spring.backend.repo.MarkRESTRepositoryImpl
import com.tor.kotlin.spring.backend.repo.SPRepository
import com.tor.kotlin.spring.backend.repo.SPRepositoryImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    internal var userDetailsService: UserDetailsServiceImpl? = null

    @Autowired
    private val unauthorizedHandler: JwtAuthEntryPoint? = null

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationJwtTokenFilter(): JwtAuthTokenFilter {
        return JwtAuthTokenFilter()
    }

    @Bean
    fun markRESTRepository(): MarkRESTRepository {
        return MarkRESTRepositoryImpl()
    }

    @Bean
    fun spRESTRepository(): SPRepository {
        return SPRepositoryImp()
    }

    /*  @Throws(Exception::class)
      override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
          authenticationManagerBuilder
                  .inMemoryAuthentication()
                  .withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN");
      }*/
    @Throws(Exception::class)
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    /* http.cors().and().csrf().disable()
     .authorizeRequests()
     .antMatchers("/auth/login", "/validate").permitAll()
     .antMatchers("/admin/**").hasAuthority("ADMIN")

     .anyRequest().authenticated()
     .and()
     .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)

     .and()
     .formLogin().permitAll()

     .and()
     .sessionManagement() .maximumSessions(1)
     .and()
     .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
     .and()
     .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login")
     .deleteCookies("JSESSIONID");*/

     */
    @Throws(Exception::class)
    override protected fun configure(http: HttpSecurity) {

        http.headers()
                .frameOptions().sameOrigin()
//                .httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000).and()
//                .xssProtection().block(false)

/*               .contentSecurityPolicy("script-src 'self'")
        Это необходимо, чтобы h2-console работала корректно в iframe и могла исполнять inline-скрипты.
        Но только в DEV профиле
        if (env.activeProfiles.contains("dev")) http.headers().frameOptions().sameOrigin() */
                .contentSecurityPolicy("script-src 'self' 'unsafe-inline'")

        //отключите подделку межсайтовых запросов, так как мы не используем файлы cookie - в противном случае ВСЕ PUT, POST, DELETE получат HTTP 403!‎
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()// доступ к API авторизации
                .antMatchers("/h2-console/**").permitAll()

                .antMatchers(

                        "/",                  // корень SPA
                        "/index.html",       // главный HTML
                        "/favicon.ico",
                        "/manifest.json",
                        "/robots.txt",
                        "/static/**",        // Vue-ресурсы
                        "/logo_logo.png",    // ручная копия
                        "/logo_logo128.gif", // ручная копия
                        "/login"                     // важно: разрешить фронтовую страницу
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}