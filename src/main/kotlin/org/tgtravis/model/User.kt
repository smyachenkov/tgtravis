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
    val telegramId: Long,

    @Column(nullable = true)
    val telegramUserName: String?,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
            name = "UserRepos",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [(JoinColumn(name = "repo_id"))]
    )
    val repos: List<User>?

) {
    constructor(telegramId: Long, telegramUserName: String?) : this(null, telegramId, telegramUserName, null)
}
