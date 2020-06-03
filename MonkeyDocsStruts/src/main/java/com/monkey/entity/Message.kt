package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Message", schema = "MonkeyDocDB")
open class Message : BaseEntity() {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "sender_id", nullable = false, insertable = false, updatable = false)
    var senderId: Int? = null

    @get:Basic
    @get:Column(name = "receiver_id", nullable = false, insertable = false, updatable = false)
    var receiverId: Int? = null

    @get:Basic
    @get:Column(name = "text", nullable = false)
    var text: String? = null

    @get:Basic
    @get:Column(name = "time", nullable = false)
    var time: Date? = null

    @get:Basic
    @get:Column(name = "is_read", nullable = false)
    var read: Boolean? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "sender_id", referencedColumnName = "id")
    var sender: User? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "receiver_id", referencedColumnName = "id")
    var receiver: User? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "senderId = $senderId " +
                    "receiverId = $receiverId " +
                    "text = $text " +
                    "time = $time " +
                    "read = $read " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Message

        if (id != other.id) return false
        if (senderId != other.senderId) return false
        if (receiverId != other.receiverId) return false
        if (text != other.text) return false
        if (time != other.time) return false
        if (read != other.read) return false
        return true
    }

}

