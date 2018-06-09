package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot

class HelloCommand(bot: TravisBot,
                   message: Message) : BasicCommand(bot, message, "start") {
    override fun process() {
        respond("Hello!")
    }
}
