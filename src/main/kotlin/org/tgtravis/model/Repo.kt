package org.tgtravis.model

import java.util.Objects
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.ManyToMany
import javax.persistence.FetchType

@Entity
data class Repo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(unique = true, nullable = false)
    var name: String = "",

    @ManyToMany(mappedBy = "repos", fetch = FetchType.EAGER)
    var users: Set<User> = mutableSetOf<User>()

) {
    constructor(name: String) : this(null, name, mutableSetOf())

    override fun toString(): String = "$id $name"

    override fun hashCode(): Int = Objects.hash(id, name)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (this::class !== other!!::class) return false
        other as Repo
        return this.id == other.id && this.name == other.name
    }
}
