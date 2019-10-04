package com.tor.kotlin.spring.backend.repo

import org.springframework.context.annotation.Bean
import java.util.*

class MarkRESTRepositoryImpl : MarkRESTRepository {
    override fun getHandsontable(humanId: Int, subjId: Int, lessonTypeId: Int, psgId: Int): String =
        this::class.java.classLoader.getResource("handsontable.json").readText()


    override fun getSubjects(humanId: Int): List<Pair<Int, String>> =
            Arrays.asList(Pair(1, "test"))

    override fun getLessonTyps(humanId: Int, subjId: Int): List<Pair<Int, String>> =
            Arrays.asList(Pair(2, "testLT"))

    override fun getPSGs(humanId: Int, subjId: Int, lessonTypeId: Int): List<Pair<Int, String>> =
            Arrays.asList(
            Pair(3, "testPSG_1"),
            Pair(4, "testPSG_2")
    )
}