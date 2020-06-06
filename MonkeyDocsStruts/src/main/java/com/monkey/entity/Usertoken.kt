package com.monkey.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkey.entity.base.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Usertoken", schema = "MonkeyDocDB", catalog = "")
open class Usertoken : BaseEntity() {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "token", nullable = false)
    var token: String? = null

    @get:Basic
    @get:Column(name = "userid", nullable = false, insertable = false, updatable = false)
    var userid: Int? = null

    @get:Basic
    @get:Column(name = "time_stamp", nullable = false)
    var timeStamp: Date? = null

    @get:JsonIgnore
    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "userid", referencedColumnName = "id")
    var refUserEntity: User? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "token = $token " +
                    "userid = $userid " +
                    "timeStamp = $timeStamp " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Usertoken

        if (id != other.id) return false
        if (token != other.token) return false
        if (userid != other.userid) return false
        if (timeStamp != other.timeStamp) return false

        return true
    }

}

