package com.tor.kotlin.spring.backend.jaas.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

/**
 * User: tor
 * Date: 02.11.2019
 * Time: 15:21
 */
class SaveMarkDTO : Serializable {
    @JsonProperty("row")
    @field:Schema(required = true)
    var row: Int? = null
    @JsonProperty("prop")
    @field:Schema(required = true)
    var column: String? = null
    @JsonProperty("newValue")
    @field:Schema(required = true)
    var valueNew: String? = null
    @JsonProperty("oldValue")
    @field:Schema(required = false)
    var valueOld: String? = null

    constructor()
    constructor(row: Int?, column: String?, valueNew: String?, valueOld: String?) {
        this.row = row
        this.column = column
        this.valueNew = valueNew
        this.valueOld = valueOld
    }

    companion object {
        private const val serialVersionUID = -1764970284520387975L
    }

    override fun toString(): String {
        return "SaveMarkDTO(row=$row, column=$column, valueNew=$valueNew, valueOld=$valueOld)"
    }

}

/*
data class SaveMarkDTO2(
        @field:Schema(required = true, name = "row") val row: Int? = null,
        @field:Schema(required = true, name = "prop") val column: String? = null,
        @field:Schema(required = true, name = "newValue") val valueNew: String? = null,
        @field:Schema(required = false, name = "oldValue") val valueOld: String? = null
)*/
