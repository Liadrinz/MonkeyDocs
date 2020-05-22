package com.monkey.entity

import com.alibaba.fastjson.annotation.JSONField
import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "Checkpoint", schema = "MonkeyDocDB", catalog = "")
open class Checkpoint : BaseEntity() {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "docid", nullable = false, insertable = false, updatable = false)
    var docid: Int? = null

    @get:Basic
    @get:Column(name = "lastDelta", nullable = false, insertable = false, updatable = false)
    var lastDelta: Int? = null

    @get:JSONField(serialize = false)
    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "docid", referencedColumnName = "id")
    var refMeta: Meta? = null

    @get:JSONField(serialize = false)
    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "lastDelta", referencedColumnName = "id")
    var refDelta: Delta? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "docid = $docid " +
                    "lastDelta = $lastDelta " +
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

        return true
    }

}

