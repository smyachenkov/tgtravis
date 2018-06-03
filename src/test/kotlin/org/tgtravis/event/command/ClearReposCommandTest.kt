package org.tgtravis.event.command

import org.mockito.Matchers
import org.mockito.Mockito.*
import org.telegram.telegrambots.api.methods.send.SendMessage
import kotlin.test.Test

class ClearReposCommandTest : AbstractCommandTest() {

    @Test
    fun respondsWithSuccess() {
        mockMessage("clear")
        val command = ClearReposCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, Matchers.anyString()))
    }
}
