package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "Fragment", schema = "MonkeyDocDB")
open class Fragment: BaseEntity<Fragment>() {
    @get:GeneratedValue
    @get:Id
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "begin", nullable = false)
    var begin: Int? = null

    @get:Basic
    @get:Column(name = "end", nullable = false)
    var end: Int? = null

    @get:Basic
    @get:Column(name = "fContent", nullable = false)
    var fContent: String? = null

    @get:Basic
    @get:Column(name = "fType", nullable = false)
    var fType: Boolean? = null

    @get:Basic
    @get:Column(name = "rowId", nullable = false, insertable = false, updatable = false)
    var rowId: Int? = null

    @get:Basic
    @get:Column(name = "userId", nullable = false, insertable = false, updatable = false)
    var userId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "rowId", referencedColumnName = "id")
    var refRow: Row? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "userId", referencedColumnName = "id")
    var refUser: User? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "begin = $begin " +
                    "end = $end " +
                    "FContent = $fContent " +
                    "FType = $fType " +
                    "rowId = $rowId " +
                    "userId = $userId " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Fragment

        if (id != other.id) return false
        if (begin != other.begin) return false
        if (end != other.end) return false
        if (fContent != other.fContent) return false
        if (fType != other.fType) return false
        if (rowId != other.rowId) return false
        if (userId != other.userId) return false

        return true
    }

}

