package org.tgtravis.event.command

import org.mockito.Mockito.*
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.User
import org.telegram.telegrambots.bots.AbsSender
import org.tgtravis.storage.RepoStorage
import kotlin.test.AfterTest

abstract class AbstractCommandTest {

    val userId = 1
    val chatId = 22L
    val bot = mock(AbsSender::class.java)
    val message = mock(Message::class.java)
    val user = mock(User::class.java)

    init {
        `when`(user.id).thenReturn(userId)
        `when`(message.chatId).thenReturn(chatId)
        `when`(message.from).thenReturn(user)
    }

    fun mockMessage(msg: String) {
        `when`(message.text).thenReturn(msg)
    }

    @AfterTest
    fun clearDb() {
        RepoStorage.clear(userId)
    }

}
