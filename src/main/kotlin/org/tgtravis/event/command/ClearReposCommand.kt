package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender
import org.tgtravis.storage.RepoStorage

class ClearReposCommand(bot: AbsSender,
                        message: Message) : BasicCommand(bot, message, "clear") {
    override fun process() {
        RepoStorage.clear(message.from.id)
        bot.execute(SendMessage(message.chatId, "Cleared!"))
    }
}
