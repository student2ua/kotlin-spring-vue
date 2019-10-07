package com.tor.kotlin.spring.backend.repo

import java.util.*

class MarkRESTRepositoryImpl : MarkRESTRepository {
    val pz = Pair(1, "Pz")
    val lecture = Pair(2, "Lecture")
    val lab = Pair(3, "Lab")

    var dataMap: Map<Pair<Int, String>, List<Pair<Int, String>>> = mapOf(
            (Pair(1, "Хвостопадство") to Arrays.asList(pz)),
            (Pair(2, "Хвосторубство") to Arrays.asList(lecture, pz)),
            (Pair(3, "Зельеварение") to Arrays.asList(lecture, lab, pz))
    )

    override fun getHandsontable(humanId: Int, subjId: Int, lessonTypeId: Int, psgId: Int): String =
            this::class.java.classLoader.getResource("handsontable.json").readText()


    override fun getSubjects(humanId: Int): List<Pair<Int, String>> = dataMap.keys.toList().sortedBy { it.second }
//            Arrays.asList(Pair(1, "SubjectTest"))

    override fun getLessonTyps(humanId: Int, subjId: Int): List<Pair<Int, String>> = dataMap.entries
            .filter { it.key.first == subjId }
            .flatMap { it.value }
            .sortedBy { it.second }
//            Arrays.asList(Pair(2, "testLT"))

    override fun getPSGs(humanId: Int, subjId: Int, lessonTypeId: Int): List<Pair<Int, String>> {
        val sliz = Pair(1, "Слизерин")
        val griff = Pair(2, "Гриффиндор")
        val puf = Pair(3, "Пуффендуй")
        val kog = Pair(4, "Когтевран")
        
        return when (Pair(subjId, lessonTypeId)) {
            Pair(1, 1) -> Arrays.asList(sliz, griff)
            Pair(2, 1), Pair(2, 2) -> Arrays.asList(sliz, puf, kog)
            else -> Arrays.asList(sliz, griff, puf, kog)
        }.sortedBy { it.second }
    }
}