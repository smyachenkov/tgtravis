package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender

class AddRepoCommand(bot: AbsSender,
                     message: Message) : BasicCommand(bot, message, "add") {
    override fun process() {
        TODO("not implemented")
    }
}
