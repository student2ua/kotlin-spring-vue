package com.tor.kotlin.spring.backend.controllers

import com.tor.kotlin.spring.backend.BackendApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

/**
 * User: tor
 */
@RunWith(SpringRunner::class)
//@DataJpaTest
@SpringBootTest(classes = [BackendApplication::class])
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BackendControllerTest {
    private lateinit var mockMvc: MockMvc // Объявляем изменяемую переменную с отложенной инициализацией в которой будем хранить mock объект
    @Autowired
    private lateinit var webAppContext: WebApplicationContext // Объявляем изменяемую переменную с отложенной инициализацией в которую будет внедрен контекст приложения
/*    @Autowired
    var repository: PersonRepository? = null*/
    @Before // Этот метод будет запущен перед каждым тестом
    fun before() {
        mockMvc = webAppContextSetup(webAppContext).build() // Создаем объект с контекстом придожения
    }

  /*  @Test
    fun testGet() {
        assert(repository!!.existsById(1))
    }*/

    @Test
    fun `1 - Get Greeting`() {
        val request = get("http://localhost:8080/api/greeting").contentType(APPLICATION_JSON) // Создаем GET запрос по адресу http://localhost:8080/products/ с http заголовком Content-Type: application/json
        val passedJsonString = """
            {
                "id": 1,
                "content": "Hello, World"
            }
        """.trimIndent()
        mockMvc.perform(request) // Выполняем запрос
                .andExpect(status().isOk) // Ожидаем http статус 200 OK
                .andExpect(content().json(passedJsonString, true)) // ожидаем пустой JSON массив в теле ответа
    }    @Test
    fun `2 - Get Persons`() {
        val request = get("http://localhost:8080/api/persons").contentType(APPLICATION_JSON)
        val passedJsonString = """
            [
            {"id": 1,"name": "John"},
            {"id": 2,"name": "Griselda"},
            {"id": 3,"name": "Bobby"}
            ]
        """.trimIndent()
        mockMvc.perform(request) // Выполняем запрос
                .andExpect(status().isOk) // Ожидаем http статус 200 OK
                .andExpect(content().json(passedJsonString, true)) // ожидаем пустой JSON массив в теле ответа
    }
}