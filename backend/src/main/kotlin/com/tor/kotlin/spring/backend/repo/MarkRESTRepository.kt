package com.tor.kotlin.spring.backend.repo

/**
 * User: tor
 * Date: 02.10.2019
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
interface MarkRESTRepository {
    fun getSubjects(humanId: Int): List<Pair<Int, String>>
    fun getLessonTyps(humanId: Int, subjId: Int): List<Pair<Int, String>>
    fun getPSGs(humanId: Int, subjId: Int, lessonTypeId: Int): List<Pair<Int, String>>
    fun getHandsontable(humanId: Int, subjId: Int, lessonTypeId: Int,psgId:Int):String
}