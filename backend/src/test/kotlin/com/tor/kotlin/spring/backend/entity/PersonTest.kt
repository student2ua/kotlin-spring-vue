package com.tor.kotlin.spring.backend.entity

import com.tor.kotlin.spring.backend.BackendApplication
import com.tor.kotlin.spring.backend.repo.PersonRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * User: tor
 */
@RunWith(SpringRunner::class)
//@DataJpaTest
@SpringBootTest(classes = [BackendApplication::class])
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class PersonTest {
    @Autowired
    var repository: PersonRepository? = null

    @Test
    fun testGet() {
        assert(repository!!.existsById(1))
    }
}