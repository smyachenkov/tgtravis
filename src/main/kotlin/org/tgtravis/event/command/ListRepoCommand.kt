package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot

class ListRepoCommand(bot: TravisBot,
                      message: Message) : BasicCommand(bot, message, "list") {
    override fun process() {
        val repos = user.repos.toSortedSet(compareBy({ it.name }))
        respond("Your current watchlist: ${repos.joinToString { it.name }}")
    }
}
