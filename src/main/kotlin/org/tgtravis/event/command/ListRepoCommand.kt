package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender
import org.tgtravis.storage.RepoStorage

class ListRepoCommand(bot: AbsSender,
                      message: Message) : BasicCommand(bot, message, "list") {
    override fun process() {
        val repos = RepoStorage.get(message.from.id)
        val response = if (repos.isEmpty()) "You don't have any repositories in watchlist"
        else repos.joinToString(", ")
        bot.execute(SendMessage(message.chatId, response))
    }
}
