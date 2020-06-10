package com.monkey.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkey.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "Delta", schema = "MonkeyDocDB", catalog = "")
open class Delta : BaseEntity() {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "content", nullable = false)
    var content: String? = null

    @get:Basic
    @get:Column(name = "docid", nullable = false, insertable = false, updatable = false)
    var docid: Int? = null

    @get:Basic
    @get:Column(name = "userid", nullable = false, insertable = false, updatable = false)
    var userid: Int? = null

    @get:JsonIgnore
    @get:OneToMany(mappedBy = "refDelta")
    var refCheckpoints: Set<Checkpoint>? = null

    @get:JsonIgnore
    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "docid", referencedColumnName = "id")
    var refMeta: Meta? = null

    @get:ManyToOne(fetch = FetchType.EAGER)
    @get:JoinColumn(name = "userid", referencedColumnName = "id")
    var refUser: User? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "content = $content " +
                    "docid = $docid " +
                    "userid = $userid " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Delta

        if (id != other.id) return false
        if (content != other.content) return false
        if (docid != other.docid) return false
        if (userid != other.userid) return false

        return true
    }

}

