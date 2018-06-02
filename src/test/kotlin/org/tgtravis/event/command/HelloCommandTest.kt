package org.tgtravis.event.command

import org.junit.jupiter.api.Test
import org.mockito.Matchers
import org.mockito.Mockito.*
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender

internal class HelloCommandTest {

    @Test
    fun respondsToStartMessage() {
        val bot = mock(AbsSender::class.java)
        val message = mock(Message::class.java)
        `when`(message.text).thenReturn("start")
        `when`(message.chatId).thenReturn(123)
        val command = HelloCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, Matchers.anyString()))
    }
}
