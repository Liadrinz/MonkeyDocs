package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "DocumentRowNode", schema = "MonkeyDocDB")
open class Row: BaseEntity<Row>() {
    @get:GeneratedValue
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "preRow", nullable = true)
    var preRow: Int? = null

    @get:Basic
    @get:Column(name = "nextRow", nullable = true)
    var nextRow: Int? = null

    @get:Basic
    @get:Column(name = "docId", nullable = false, insertable = false, updatable = false)
    var docId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "docId", referencedColumnName = "id")
    var refMeta: Meta? = null

    @get:OneToMany(mappedBy = "refRow", fetch = FetchType.EAGER)
    var refFragments: Set<Fragment>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "preRow = $preRow " +
                    "nextRow = $nextRow " +
                    "docId = $docId " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Row

        if (id != other.id) return false
        if (preRow != other.preRow) return false
        if (nextRow != other.nextRow) return false
        if (docId != other.docId) return false

        return true
    }

}

