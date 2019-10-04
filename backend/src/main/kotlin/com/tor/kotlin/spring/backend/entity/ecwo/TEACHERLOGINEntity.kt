package  com.tor.kotlin.spring.backend.entity.ecwo

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "TEACHERLOGIN", schema = "UNIVERSITYAUDIT", catalog = "")
open class TEACHERLOGINEntity {
    @get:Id
    @get:Column(name = "ID", nullable = false, insertable = false, updatable = false)
    var ID: Int? = null
    @get:Basic
    @get:Column(name = "HUMANID", nullable = false, insertable = false, updatable = false)
    var HUMANID: Int? = null
    @get:Basic
    @get:Column(name = "LOGIN", nullable = false, length = 20)
    var LOGIN: String? = null
    @get:Basic
    @get:Column(name = "PASSWORD", nullable = false, length = 50)
    @get:Size(min = 1, max = 20)
    var PASSWORD: String? = null

    //    @get:OneToMany(mappedBy = "refTEACHERLOGINEntity")
//    var refTEACHERGROUPTLLINKEntities: List<TEACHERGROUPTLLINKEntity>? = null
    //    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "HUMANID", referencedColumnName = "HUMANID")
    @get:Column(name = "HUMANID", nullable = false)
    var humanid: Int? = null
    /* @get:OneToMany(mappedBy = "refTEACHERLOGINEntity")
     var refTLGROUPLINKEntities: List<TLGROUPLINKEntity>? = null*/

    @get:ManyToMany(fetch = FetchType.EAGER)
    @get:JoinTable(schema = "UNIVERSITYAUDIT", name = "TLGROUPLINK",
            joinColumns = [JoinColumn(name = "TLOGINID")],
            inverseJoinColumns = [JoinColumn(name = "GROUPID")])
    var roleGroups: List<TLGROUPEntity>? = null

  /*  @get:ManyToMany
    @get:JoinTable(schema = "UNIVERSITYAUDIT", name = "TEACHERGROUP_TL_LINK",
            joinColumns = [JoinColumn(name = "TLID", referencedColumnName = "ID")],
            inverseJoinColumns = [JoinColumn(name = "TGROUPID", referencedColumnName = "GROUPID")]) */
    @get:ManyToMany(mappedBy = "teacherlogins")
    var  teacherGroups:List<TEACHERGROUPEntity>?=null
    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "ID = $ID " +
                    "HUMANID = $HUMANID " +
                    "LOGIN = $LOGIN " +
                    "PASSWORD = $PASSWORD " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as TEACHERLOGINEntity

        if (ID != other.ID) return false
        if (HUMANID != other.HUMANID) return false
        if (LOGIN != other.LOGIN) return false
        if (PASSWORD != other.PASSWORD) return false

        return true
    }

}

