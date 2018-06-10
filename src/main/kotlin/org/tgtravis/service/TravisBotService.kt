package org.tgtravis.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.model.Repo
import org.tgtravis.model.User
import org.tgtravis.repository.RepoRepository
import org.tgtravis.repository.UserRepository

@Service
class TravisBotService

@Autowired constructor(
    private val repos: RepoRepository,
    private val users: UserRepository
) {

    fun retrieveUser(message: Message): User {
        val existing = users.findByTelegramId(message.from.id.toLong())
        return if (existing.isPresent)
            existing.get()
        else
            users.save(User(message.from.id.toLong(), message.from.userName))
    }

    fun retrieveRepo(name: String): Repo {
        val existing = repos.findByName(name)
        return if (existing.isPresent)
            existing.get()
        else
            repos.save(Repo(name))
    }

    fun retrieveRepos(names: Set<String>): Set<Repo> = names.map { retrieveRepo(it) }.toSet()

    fun addRepos(user: User, repos: Set<Repo>) {
        if (repos.isEmpty())
            return
        val current = user.repos
        if (current.isEmpty()) {
            user.repos = repos
            users.save(user)
            return
        }
        val updated = current.toMutableSet()
        updated.addAll(repos)
        user.repos = updated
        users.save(user)
    }

    fun removeRepos(user: User, repos: Set<Repo>) {
        if (repos.isEmpty() || user.repos.isEmpty())
            return
        val current = user.repos
        val updated = current.toMutableSet()
        updated.removeAll(repos)
        user.repos = updated
        users.save(user)
    }

    fun clear(user: User) {
        user.repos = mutableSetOf()
        users.save(user)
    }

    fun retrieveUsers(repoName: String): Set<User> {
        val repo = repos.findByName(repoName)
        return if (repo.isPresent) repo.get().users else emptySet()
    }
}
