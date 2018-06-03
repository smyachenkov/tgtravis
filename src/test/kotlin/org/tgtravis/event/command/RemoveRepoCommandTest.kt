package org.tgtravis.event.command

import org.mockito.Matchers
import org.mockito.Mockito.*
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.User
import org.telegram.telegrambots.bots.AbsSender
import kotlin.test.Test

class RemoveRepoCommandTest {

    @Test
    fun respondsWithSuccess() {
        val bot = mock(AbsSender::class.java)
        val message = mock(Message::class.java)
        val user = mock(User::class.java)
        `when`(user.id).thenReturn(1)
        `when`(message.text).thenReturn("start")
        `when`(message.chatId).thenReturn(123)
        `when`(message.from).thenReturn(user)
        val command = RemoveRepoCommand(bot, message)
        command.process()
        verify(bot).execute(SendMessage(message.chatId, Matchers.anyString()))
    }
}
