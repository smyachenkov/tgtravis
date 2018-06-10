package org.tgtravis.event.noification

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.tgtravis.TravisBot
import org.tgtravis.model.User

abstract class BasicNotification(val bot: TravisBot) : Notification {

    override fun notifyUsers(users: Set<User>, message: String) {
        users.forEach {
            val sendMessage = SendMessage(it.telegramId, message)
            sendMessage.enableMarkdown(true)
            bot.execute(sendMessage)
        }
    }
}
