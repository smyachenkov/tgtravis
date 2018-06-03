package org.tgtravis.event.command

import org.mockito.Mockito.*
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.tgtravis.storage.RepoStorage
import kotlin.test.Test

class ListRepoCommandTest : AbstractCommandTest() {

    @Test
    fun respondsToUserWithoutRepos() {
        mockMessage("list")
        val command = ListRepoCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, "You don't have any repositories in watchlist"))
    }

    @Test
    fun respondsToUserWithRepos() {
        RepoStorage.add(userId, listOf("repo1"))
        mockMessage("list")
        val command = ListRepoCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, listOf("repo1").joinToString(", ")))
    }
}
