package org.tgtravis.event.command

import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.User
import org.tgtravis.TravisBot
import org.tgtravis.service.TravisBotService

abstract class AbstractCommandTest {

    private val service = mock(TravisBotService::class.java)

    val userId = 1
    val chatId = 22L
    val bot = mock(TravisBot::class.java)
    val message = mock(Message::class.java)
    val user = mock(User::class.java)

    init {
        `when`(user.id).thenReturn(userId)
        `when`(message.chatId).thenReturn(chatId)
        `when`(message.from).thenReturn(user)
        `when`(bot.service).thenReturn(service)
        `when`(service.retrieveUser(message)).thenReturn(org.tgtravis.model.User(userId.toLong()))
    }

    fun mockMessage(msg: String) {
        `when`(message.text).thenReturn(msg)
    }

    fun mockUser(user: org.tgtravis.model.User) {
        `when`(service.retrieveUser(message)).thenReturn(user)
    }

}
