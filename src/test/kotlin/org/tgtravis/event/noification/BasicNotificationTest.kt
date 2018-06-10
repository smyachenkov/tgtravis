package org.tgtravis.event.noification

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.tgtravis.TravisBot
import org.tgtravis.model.User

class BasicNotificationTest {

    private val bot = mock(TravisBot::class.java)

    class TestNotification(bot: TravisBot) : BasicNotification(bot) {
        override fun process() {}
        fun testNotify(users: Set<User>, message: String) {
            notifyUsers(users, message)
        }
    }

    @Test
    fun notifyUsersDoesNothingToEmptyList() {
        TestNotification(bot).testNotify(emptySet(), "message")
        verify(bot, never()).execute(any<SendMessage>())
    }

    @Test
    fun notifyUsersSendsMessageToAllUsersInList() {
        val users = setOf(User(1), User(2), User(3))
        TestNotification(bot).testNotify(users, "message")
        verify(bot, times(3)).execute(any<SendMessage>())
    }
}
