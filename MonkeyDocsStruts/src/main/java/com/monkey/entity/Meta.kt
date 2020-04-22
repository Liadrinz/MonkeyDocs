package com.monkey.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.monkey.entity.base.BaseEntity
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "DocumentMeta", schema = "MonkeyDocDB")
open class Meta : BaseEntity<Meta>() {
    @get:GeneratedValue
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "mdName", nullable = false)
    var mdName: String? = null

    @get:Basic
    @get:Column(name = "isRecycled", nullable = false)
    var isRecycled: Boolean = false

    @get:Basic
    @get:Column(name = "createTime", nullable = false)
    var createTime: Timestamp? = null

    @get:Basic
    @get:Column(name = "updateTime", nullable = false)
    var updateTime: Timestamp? = null

    @get:Basic
    @get:Column(name = "firstRowId", nullable = false, insertable = false, updatable = false)
    var firstRowId: Int? = null

    @get:JsonBackReference
    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "firstRowId", referencedColumnName = "id")
    var refRow: Row? = null

    @get:OneToMany(mappedBy = "refMeta", fetch = FetchType.EAGER)
    var refMetaToUsers: Set<MetaToUser>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "mdName = $mdName " +
                    "isRecycled = $isRecycled " +
                    "createTime = $createTime " +
                    "updateTime = $updateTime " +
                    "firstRowId = $firstRowId " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Meta

        if (id != other.id) return false
        if (mdName != other.mdName) return false
        if (isRecycled != other.isRecycled) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false
        if (firstRowId != other.firstRowId) return false

        return true
    }

}

