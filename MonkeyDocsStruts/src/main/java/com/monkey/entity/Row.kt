package com.monkey.entity

import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "DocumentRowNode", schema = "MonkeyDocDB")
@IdClass(DocumentRowNodeEntityPK::class)
open class Row : BaseEntity<Row>() {
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

    @get:Id
    @get:Column(name = "docId", nullable = false, insertable = false, updatable = false)
    var docId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "docId", referencedColumnName = "id")
    var refMeta: Meta? = null

//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "id", referencedColumnName = "rowId")
//    var refFragment: Fragment? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "preRow = $preRow " +
                    "nextRow = $nextRow " +
                    "docid = $docId " +
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

class DocumentRowNodeEntityPK : java.io.Serializable {
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @get:Id
    @get:Column(name = "docid", nullable = false, insertable = false, updatable = false)
    var docid: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as DocumentRowNodeEntityPK

        if (id != other.id) return false
        if (docid != other.docid) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
