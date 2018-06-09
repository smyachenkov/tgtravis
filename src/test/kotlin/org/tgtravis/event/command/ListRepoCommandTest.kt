package org.tgtravis.event.command

import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.tgtravis.model.Repo
import org.tgtravis.model.User
import kotlin.test.Test

class ListRepoCommandTest : AbstractCommandTest() {

    @Test
    fun respondsToUserWithoutRepos() {
        mockMessage("list")
        val command = spy(ListRepoCommand(bot, message))
        command.process()
        verify(bot).execute(SendMessage(message.chatId, "Your current watchlist: "))
    }

    @Test
    fun respondsToUserWithRepos() {
        mockMessage("list")
        val user = User(1, "user")
        user.repos = setOf(Repo("repo1"), Repo("repo2"))
        mockUser(user)
        val command = ListRepoCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId,
                "Your current watchlist: ${listOf("repo1", "repo2").joinToString(", ")}"))
    }
}
