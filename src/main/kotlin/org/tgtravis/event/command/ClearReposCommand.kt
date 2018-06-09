package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot

class ClearReposCommand(bot: TravisBot, message: Message) : BasicCommand(bot, message, "clear") {
    override fun process() {
        bot.service.clear(user)
        respond("Cleared!")
    }
}
