package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender
import org.tgtravis.storage.RepoStorage

class RemoveRepoCommand(bot: AbsSender,
                        message: Message) : BasicCommand(bot, message, "remove") {
    override fun process() {
        RepoStorage.remove(message.from.id, params)
        bot.execute(SendMessage(message.chatId, "Removed!"))
    }
}
