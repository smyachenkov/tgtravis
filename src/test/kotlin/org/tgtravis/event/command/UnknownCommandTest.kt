package org.tgtravis.event.command

import org.mockito.Mockito.*
import org.telegram.telegrambots.api.methods.send.SendMessage
import kotlin.test.Test

class UnknownCommandTest : AbstractCommandTest() {

    @Test
    fun respondsToAnyUnknownMessage() {
        mockMessage("unknown")
        val command = UnknownCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, "I don't know this command"))
    }
}
