package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot

class RemoveRepoCommand(bot: TravisBot,
                        message: Message) : BasicCommand(bot, message, "remove") {
    override fun process() {
        val repos = bot.service.retrieveRepos(params.toSet())
        bot.service.removeRepos(user, repos)
        respond("Removed!")
    }
}
