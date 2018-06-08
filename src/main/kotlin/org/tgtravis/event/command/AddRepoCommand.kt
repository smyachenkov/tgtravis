package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot

class AddRepoCommand(bot: TravisBot,
                     message: Message) : BasicCommand(bot, message, "add") {
    override fun process() {
        val newRepos = bot.service.retrieveRepos(params.toSet())
        bot.service.addRepos(user, newRepos)
        bot.execute(SendMessage(message.chatId, "Added!"))
    }
}
