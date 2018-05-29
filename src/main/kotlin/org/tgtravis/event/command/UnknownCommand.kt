package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender

class UnknownCommand(bot: AbsSender, message: Message) : BasicCommand(bot, message) {
    override fun process() {
        bot.execute(SendMessage(message.chatId ,"I don't know this command"))
    }
}
