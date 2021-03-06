package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Checkpoint", schema = "MonkeyDocDB")
open class Checkpoint : BaseEntity() {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "docid", nullable = false, insertable = false, updatable = false)
    var docid: Int? = null

    @get:Basic
    @get:Column(name = "time")
    var time: Date? = null

    @get:Basic
    @get:Column(name = "lastDelta", nullable = false, insertable = false, updatable = false)
    var lastDelta: Int? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "docid", referencedColumnName = "id")
    var refMeta: Meta? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "lastDelta", referencedColumnName = "id")
    var refDelta: Delta? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "docid = $docid " +
                    "lastDelta = $lastDelta " +
                    "time = $time " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Checkpoint

        if (id != other.id) return false
        if (docid != other.docid) return false
        if (lastDelta != other.lastDelta) return false
        if (time != other.time) return false
        return true
    }

}

