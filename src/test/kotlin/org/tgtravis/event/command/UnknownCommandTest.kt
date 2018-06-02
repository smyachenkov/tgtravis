package org.tgtravis.event.command

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender

internal class UnknownCommandTest {

    @Test
    fun respondsToAnyUnknownMessage() {
        val bot = mock(AbsSender::class.java)
        val message = mock(Message::class.java)
        `when`(message.text).thenReturn("nothing")
        `when`(message.chatId).thenReturn(123)
        val command = UnknownCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, "I don't know this command"))
    }
}
