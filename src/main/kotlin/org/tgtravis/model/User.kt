package org.tgtravis.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.ManyToMany
import javax.persistence.CascadeType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(unique = true, nullable = false)
    var telegramId: Long,

    @Column(nullable = true)
    var telegramUserName: String?,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
            name = "UserRepos",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [(JoinColumn(name = "repo_id"))]
    )
    var repos: Set<Repo>?

) {
    constructor(telegramId: Long, telegramUserName: String?) : this(null, telegramId, telegramUserName, null)
}
