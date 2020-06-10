package com.monkey.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "User", schema = "MonkeyDocDB", catalog = "")
open class User : BaseEntity() {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @get:Basic
    @get:Column(name = "password", nullable = false)
    var password: String? = null

    @get:JsonBackReference("refDeltas")
    @get:OneToMany(mappedBy = "refUser")
    var refDeltas: Set<Delta>? = null

    @get:JsonBackReference("refMetaToUsers")
    @get:OneToMany(mappedBy = "refUser")
    var refMetaToUsers: Set<MetaToUser>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "tel = $tel " +
                    "email = $email " +
                    "userName = $userName " +
                    "password = $password " +
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
        if (password != other.password) return false

        return true
    }

}

