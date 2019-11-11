package com.tor.kotlin.spring.backend.controllers

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.tor.kotlin.spring.backend.BackendApplication
import com.tor.kotlin.spring.backend.jaas.model.LoginUser
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import java.io.IOException


/**
 * User: tor
 * Date: 04.11.2019
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringRunner::class)
//@DataJpaTest
@SpringBootTest(classes = [BackendApplication::class])
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthControllerTest {
    private lateinit var mockMvc: MockMvc // Объявляем изменяемую переменную с отложенной инициализацией в которой будем хранить mock объект
    @Autowired
    private lateinit var webAppContext: WebApplicationContext // Объявляем изменяемую переменную с отложенной инициализацией в которую будет внедрен контекст приложения

    @Before
    fun before() {
        // Создаем объект с контекстом придожения
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build()
    }

    @Test
    fun authenticateUser() {
        val request = MockMvcRequestBuilders
                .post("http://localhost:8080/api/auth/signin")
                .content(convertObjectToJsonString(LoginUser("tor","optom")))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
     mockMvc.perform(request) // Выполняем запрос
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk) // Ожидаем http статус 200 OK
//                .andExpect(jsonPath("$", hasSize<Int>(1)))
                .andExpect(jsonPath("$.accessToken", `is`(notNullValue())))
                .andExpect(jsonPath("$.type", `is`("Bearer")))
                .andExpect(jsonPath("$.username",`is`("tor")))
//                .andExpect(jsonPath("$.authorities[:1].authority", `is`("ROLE_ADMIN")))
                .andExpect(jsonPath("$.authorities[:1].authority", `is`(notNullValue())))
//                .andExpect(MockMvcResultMatchers.content().json(passedJsonString, true))
//            "username":"tor","authorities":[{"authority":"ROLE_ADMIN"}],"type":"Bearer"}
    }

    @Test
    fun registerUser() {
    }

    @Throws(IOException::class)
    private fun convertObjectToJsonString(`object`: Any): String {
        val mapper = ObjectMapper()
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return mapper.writeValueAsString(`object`)
    }

}