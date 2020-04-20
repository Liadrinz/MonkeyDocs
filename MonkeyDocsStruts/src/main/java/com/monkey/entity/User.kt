package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "User", schema = "MonkeyDocDB")
open class User : BaseEntity<User>() {
    @get:GeneratedValue
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "tel", nullable = true)
    var tel: String? = null

    @get:Basic
    @get:Column(name = "email", nullable = true)
    var email: String? = null

    @get:Basic
    @get:Column(name = "userName", nullable = false)
    var userName: String? = null

    @get:OneToMany(mappedBy = "refUser", fetch = FetchType.EAGER)
    var refMetaToUsers: Set<MetaToUser>? = null

    @get:OneToMany(mappedBy = "refUser", fetch = FetchType.EAGER)
    var refFragments: Set<Fragment>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "tel = $tel " +
                    "email = $email " +
                    "userName = $userName " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User

        if (id != other.id) return false
        if (tel != other.tel) return false
        if (email != other.email) return false
        if (userName != other.userName) return false

        return true
    }

}

