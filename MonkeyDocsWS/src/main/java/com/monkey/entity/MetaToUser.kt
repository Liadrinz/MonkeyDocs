package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "DocumentMeta_has_User", schema = "MonkeyDocDB", catalog = "")
open class MetaToUser : BaseEntity() {
    @get:Id
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "mdId", nullable = false, insertable = false, updatable = false)
    var mdId: Int? = null

    @get:Basic
    @get:Column(name = "userId", nullable = false, insertable = false, updatable = false)
    var userId: Int? = null

    @get:Basic
    @get:Column(name = "role", nullable = false)
    var role: String? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "mdId", referencedColumnName = "id")
    var refMeta: Meta? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "userId", referencedColumnName = "id")
    var refUser: User? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "mdId = $mdId " +
                    "userId = $userId " +
                    "role = $role " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as MetaToUser

        if (id != other.id) return false
        if (mdId != other.mdId) return false
        if (userId != other.userId) return false
        if (role != other.role) return false

        return true
    }

}

