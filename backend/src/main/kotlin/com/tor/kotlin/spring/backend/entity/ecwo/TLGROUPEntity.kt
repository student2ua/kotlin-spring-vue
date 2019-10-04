package  com.tor.kotlin.spring.backend.entity.ecwo

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "TLGROUP", schema = "UNIVERSITYAUDIT")
//@UniqueConstraint(name="TLROLE_NAME_U",columnNames = {@Col})
open class TLGROUPEntity {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TLGROUP_SEQ")
    @get:SequenceGenerator(name = "TLGROUP_SEQ", schema = "UNIVERSITYAUDIT")
    @get:Column(name = "GROUPID", nullable = false, insertable = false, updatable = false)
    var GROUPID: Int? = null
    @get:Basic
    @get:Column(name = "NAME", nullable = false, unique = true, length = 40)
    @get:Size(max = 40)
    var NAME: String? = null

    @get:ManyToMany(fetch = FetchType.EAGER)
    @get:JoinTable(name = "TLGROUPTOROLELINK", schema = "UNIVERSITYAUDIT",
            inverseJoinColumns = [JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")],
            joinColumns = [JoinColumn(name = "GROUPID", referencedColumnName = "GROUPID")])
    var roles: List<TLROLESEntity>? = null

    @get:ManyToMany(mappedBy = "roleGroups")
    var teacherLogins: List<TEACHERLOGINEntity>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "GROUPID = $GROUPID " +
                    "NAME = $NAME " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as TLGROUPEntity

        if (GROUPID != other.GROUPID) return false
        if (NAME != other.NAME) return false

        return true
    }

}

