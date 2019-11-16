package com.tor.kotlin.spring.backend.jaas.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * User: tor
 * Date: 02.11.2019
 * Time: 15:21
 */
class SaveMarkDTO : Serializable {
    @JsonProperty("row")
    var row: Int? = null
    @JsonProperty("prop")
    var column: String? = null
    @JsonProperty("newValue")
    var valueNew: String? = null
    @JsonProperty("oldValue")
    var valueOld: String? = null
    constructor()
    constructor(row: Int?, column: String?, valueNew: String?, valueOld: String?) {
        this.row = row
        this.column = column
        this.valueNew = valueNew
        this.valueOld = valueOld
    }
    companion object{
        private const val serialVersionUID = -1764970284520387975L
    }

    override fun toString(): String {
        return "SaveMarkDTO(row=$row, column=$column, valueNew=$valueNew, valueOld=$valueOld)"
    }

}