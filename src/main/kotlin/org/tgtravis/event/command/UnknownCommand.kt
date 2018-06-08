package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot

class UnknownCommand(bot: TravisBot,
                     message: Message) : BasicCommand(bot, message, "unknown") {
    override fun process() {
        bot.execute(SendMessage(message.chatId, "I don't know this command"))
    }
}
