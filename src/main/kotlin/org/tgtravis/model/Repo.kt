package org.tgtravis.model

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
    var users: Set<User>? = HashSet()

) {
    constructor(name: String) : this(null, name, null)
}
