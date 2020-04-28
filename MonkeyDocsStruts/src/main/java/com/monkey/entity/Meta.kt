package com.monkey.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.monkey.entity.base.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "DocumentMeta", schema = "MonkeyDocDB")
open class Meta: BaseEntity<Meta>() {
    @get:GeneratedValue
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "mdName", nullable = false)
    var mdName: String? = null

    @get:Basic
    @get:Column(name = "isRecycled", nullable = false)
    var recycled: Boolean = false

    @get:Basic
    @get:Column(name = "createTime", nullable = false)
    var createTime: Date? = null

    @get:Basic
    @get:Column(name = "updateTime", nullable = false)
    var updateTime: Date? = null

    @get:JsonBackReference
    @get:OneToMany(mappedBy = "refMeta", fetch = FetchType.EAGER)
    var refMetaToUsers: Set<MetaToUser>? = null

    @get:JsonBackReference
    @get:OneToMany(mappedBy = "refMeta", fetch = FetchType.EAGER)
    var refRows: Set<Row>? = null

    @get:ManyToMany(fetch = FetchType.EAGER, mappedBy = "refMetas")
    var refUsers: Set<User>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "mdName = $mdName " +
                    "isRecycled = $recycled " +
                    "createTime = $createTime " +
                    "updateTime = $updateTime " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Meta

        if (id != other.id) return false
        if (mdName != other.mdName) return false
        if (recycled != other.recycled) return false
        if (createTime != other.createTime) return false
        if (updateTime != other.updateTime) return false

        return true
    }

}

