package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot

class UnknownCommand(bot: TravisBot, message: Message) : BasicCommand(bot, message, "unknown") {
    override fun process() {
        respond("I don't know this command")
    }
}
