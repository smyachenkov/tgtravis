package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender
import org.tgtravis.storage.RepoStorage

class AddRepoCommand(bot: AbsSender,
                     message: Message) : BasicCommand(bot, message, "add") {
    override fun process() {
        RepoStorage.add(message.from.id, params)
        bot.execute(SendMessage(message.chatId, "Added!"))
    }
}
