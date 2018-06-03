package org.tgtravis.event.command

import org.mockito.Matchers
import org.mockito.Mockito.*
import org.telegram.telegrambots.api.methods.send.SendMessage
import kotlin.test.Test

class RemoveRepoCommandTest : AbstractCommandTest() {

    @Test
    fun respondsWithSuccess() {
        mockMessage("remove")
        val command = RemoveRepoCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, Matchers.anyString()))
    }
}
