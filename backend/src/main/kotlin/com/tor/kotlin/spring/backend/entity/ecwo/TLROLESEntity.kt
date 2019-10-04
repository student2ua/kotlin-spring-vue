package  com.tor.kotlin.spring.backend.entity.ecwo

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "TLROLES", schema = "UNIVERSITYAUDIT")
open class TLROLESEntity {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TLROLES_SEQ")
    @get:SequenceGenerator(name = "TLROLES_SEQ", schema = "UNIVERSITYAUDIT", sequenceName = "TLROLES_SEQ")
    @get:Column(name = "ROLEID", nullable = false, insertable = false, updatable = false)
    var ROLEID: Int? = null
    @get:Basic
    @get:Column(name = "NAME", nullable = false, unique = true, length = 50)
    @get:Size(max = 50)
    var NAME: String? = null

    /* @get:OneToMany(mappedBy = "refTLROLESEntity")
     var refTLGROUPTOROLELINKEntities: List<TLGROUPTOROLELINKEntity>? = null
 */
    @get:ManyToMany(mappedBy = "roles")
    var roleGroups: List<TLGROUPEntity>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "ROLEID = $ROLEID " +
                    "NAME = $NAME " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as TLROLESEntity

        if (ROLEID != other.ROLEID) return false
        if (NAME != other.NAME) return false

        return true
    }

}

