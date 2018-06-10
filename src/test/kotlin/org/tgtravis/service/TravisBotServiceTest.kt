package org.tgtravis.service

import org.mockito.Mockito.`when`
import org.mockito.Mockito.atMost
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.model.Repo
import org.tgtravis.model.User
import org.tgtravis.repository.RepoRepository
import org.tgtravis.repository.UserRepository
import java.util.Optional
import java.util.concurrent.ThreadLocalRandom
import kotlin.test.Test
import kotlin.test.assertEquals

class TravisBotServiceTest {

    private val repos = mock(RepoRepository::class.java)

    private val users = mock(UserRepository::class.java)

    private val service = spy(org.tgtravis.service.TravisBotService(repos, users))

    @Test
    fun retrieveUserFindsExisting() {
        val id = ThreadLocalRandom.current().nextInt()
        val message = mockMessage(id, null)
        val user = User(id.toLong(), null)
        `when`(users.findByTelegramId(message.from.id.toLong())).thenReturn(Optional.of(user))
        assertEquals(user, service.retrieveUser(message), "must return existing user")
    }

    @Test
    fun retrieveUserCreatesNew() {
        val id = ThreadLocalRandom.current().nextInt()
        val message = mockMessage(id, null)
        `when`(users.findByTelegramId(message.from.id.toLong())).thenReturn(Optional.empty())
        `when`(users.save(User(id.toLong(), null))).thenReturn(User(id.toLong(), null))
        assertEquals(User(id.toLong(), null), service.retrieveUser(message),
                "must create new user if not found")
    }

    @Test
    fun retrieveRepoFindsExisting() {
        val name = "repo"
        val repo = Repo(name)
        `when`(repos.findByName(name)).thenReturn(Optional.of(repo))
        assertEquals(Repo(name), service.retrieveRepo(name),
                "must return existing repo")
    }

    @Test
    fun retrieveRepoCreatesNew() {
        val name = "repo"
        `when`(repos.findByName(name)).thenReturn(Optional.empty())
        `when`(repos.save(Repo(name))).thenReturn(Repo(name))
        assertEquals(Repo(name), service.retrieveRepo(name),
                "must create new repo if not not found")
    }

    @Test
    fun retrieveReposReturnsCorrectSet() {
        val input = setOf("repo1", "repo2")
        `when`(repos.findByName("repo1")).thenReturn(Optional.of(Repo("repo1")))
        `when`(repos.findByName("repo2")).thenReturn(Optional.of(Repo("repo2")))
        val result = service.retrieveRepos(input)
        assertEquals(2, result.size,
                "must contain all repos")
    }

    @Test
    fun addReposAddsEmptySet() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf(Repo("repo1"), Repo("repo2"))
        service.addRepos(user, emptySet())
        verify(users, never()).save(user)
        assertEquals(2, user.repos.size,
                "must not change repo list with empty set on input")
    }

    @Test
    fun clearReposIgnoresEmptyInput() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf(Repo("repo1"), Repo("repo2"))
        service.removeRepos(user, setOf())
        verify(users, never()).save(user)
        assertEquals(2, user.repos.size,
                "must ignore empty input set")
    }

    @Test
    fun clearReposIgnoresEmptyUserRepoList() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf()
        service.removeRepos(user, setOf(Repo("repo1"), Repo("repo2")))
        verify(users, never()).save(user)
        assertEquals(0, user.repos.size,
                "must ignore empty user repo list")
    }

    @Test
    fun clearReposRemovesOnlyPresentRepos() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf(Repo("repo1"), Repo("repo2"), Repo("repo3"))
        service.removeRepos(user, setOf(Repo("repo2"), Repo("repo3"), Repo("repo999")))
        verify(users, atMost(1)).save(user)
        assertEquals(1, user.repos.size, "must remove only present in input repos")
        assertEquals(true, user.repos.contains(Repo("repo1")),
                "must remove only present in input repos")
    }

    @Test
    fun addReposAddsNewToCurrentPresent() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf(Repo("repo1"), Repo("repo2"))
        service.addRepos(user, setOf(Repo("repo3"), Repo("repo4")))
        assertEquals(4, user.repos.size, "must add new values to present list")
    }

    @Test
    fun addReposMustIgnoreDuplicates() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf(Repo("repo1"), Repo("repo2"))
        service.addRepos(user, setOf(Repo("repo2"), Repo("repo3")))
        assertEquals(3, user.repos.size, "must not add duplicate repos")
    }

    @Test
    fun addReposAddsNewToCurrentEmpty() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf()
        service.addRepos(user, setOf(Repo("repo2"), Repo("repo3")))
        assertEquals(2, user.repos.size, "must add new values to empty list")
    }

    @Test
    fun clearRemovesAllRepos() {
        val user = User(ThreadLocalRandom.current().nextLong())
        user.repos = mutableSetOf(Repo("repo1"), Repo("repo2"))
        service.clear(user)
        assertEquals(0, user.repos.size, "must remove all repos from user")
    }

    @Test
    fun retrieveUsersByRepoFindsUsers() {
        val users = setOf(User(1), User(2))
        val repo = Repo("repo")
        repo.users = users
        `when`(repos.findByName("repo")).thenReturn(Optional.of(repo))
        assertEquals(users, service.retrieveUsers("repo"),
                "must return all users of found repo")
    }

    @Test
    fun retrieveUsersByRepoReturnsEmptySetIfNotFound() {
        val repo = Repo("repo")
        `when`(repos.findByName("repo")).thenReturn(Optional.of(repo))
        assertEquals(emptySet(), service.retrieveUsers("repo"),
                "must return empty set when nobody's watching repo")
    }

    private fun mockMessage(id: Int, username: String?): Message {
        val message = mock(Message::class.java)
        val from = mock(org.telegram.telegrambots.api.objects.User::class.java)
        `when`(from.id).thenReturn(id)
        `when`(from.userName).thenReturn(username)
        `when`(message.from).thenReturn(from)
        return message
    }
}
