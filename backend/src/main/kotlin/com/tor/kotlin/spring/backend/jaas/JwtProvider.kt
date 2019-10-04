package com.tor.kotlin.spring.backend.jaas

//import com.tor.kotlin.spring.backend.repo.UsersRepository
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
public class JwtProvider {
    private val logger = LoggerFactory.getLogger(JwtProvider::class.java)

    /*  @Autowired
      lateinit var usersRepository: UsersRepository*/
    @Value("\${assm.app.jwtSecret}")
    lateinit var jwtSecret: String
    @Value("\${assm.app.jwtExpiration}")
    var jwtExpiration: Int? = 0

    fun generateJwtToken(username: String): String {
        logger.debug("username=" + username)
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date())
                .setExpiration(Date((Date()).getTime() + jwtExpiration!! * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            logger.debug("validateJwtToken " + authToken)
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature -> Message: {} ", e)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token -> Message: {}", e)
        } catch (e: ExpiredJwtException) {
            logger.error("Expired JWT token -> Message: {}", e)
        } catch (e: UnsupportedJwtException) {
            logger.error("Unsupported JWT token -> Message: {}", e)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty -> Message: {}", e)
        }
        return false
    }

    fun getUserNameFromJwtToken(token: String): String {
        logger.info("getUserNameFromJwtToken:$token")
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject()
    }
}