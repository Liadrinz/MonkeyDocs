package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import java.util.*
import javax.persistence.*;

@Entity
@Table(name = "Docvisittoken", schema = "MonkeyDocDB", catalog = "")
open class Docvisittoken : BaseEntity() {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "mdId", nullable = false, insertable = false, updatable = false)
    var mdId: Int? = null

    @get:Basic
    @get:Column(name = "createtime", nullable = false)
    var createtime: Date? = Date()

    @get:Basic
    @get:Column(name = "token", nullable = false)
    var token: String? = null

    @get:Basic
    @get:Column(name = "tokentype", nullable = false)
    var tokentype: String? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "mdId", referencedColumnName = "id")
    var refMeta: Meta? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "mdId = $mdId " +
                    "createtime = $createtime " +
                    "token = $token " +
                    "tokentype= $tokentype"+
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Docvisittoken

        if (id != other.id) return false
        if (mdId != other.mdId) return false
        if (createtime != other.createtime) return false
        if (token != other.token) return false
        if (tokentype != other.tokentype) return false
        return true
    }
}