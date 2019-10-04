package  com.tor.kotlin.spring.backend.entity.ecwo

import javax.persistence.*

@Entity
@Table(name = "TEACHERGROUP", schema = "UNIVERSITYAUDIT", catalog = "")
open class TEACHERGROUPEntity {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teachergroup_seq")
    @get:SequenceGenerator(name="teachergroup_seq",sequenceName = "teachergroup_seq", schema = "UNIVERSITYAUDIT" )
    @get:Column(name = "GROUPID", nullable = false, insertable = false, updatable = false)
    var GROUPID: Long? = null
    @get:Basic
    @get:Column(name = "NAME", nullable = false)
    var NAME: String? = null

 /*   @get:OneToMany(mappedBy = "refTEACHERGROUPEntity")
    var refTEACHERGROUPTLLINKEntities: List<TEACHERGROUPTLLINKEntity>? = null
*/
    @get:ManyToMany
    @get:JoinTable(schema = "UNIVERSITYAUDIT", name = "TEACHERGROUP_TL_LINK",
            joinColumns = [JoinColumn(name = "TGROUPID")],
            inverseJoinColumns = [JoinColumn(name = "TLID")])
    var teacherlogins: List<TEACHERLOGINEntity>? = null

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
        other as TEACHERGROUPEntity

        if (GROUPID != other.GROUPID) return false
        if (NAME != other.NAME) return false

        return true
    }

}

