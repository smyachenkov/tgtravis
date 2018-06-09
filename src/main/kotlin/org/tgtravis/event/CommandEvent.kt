package org.tgtravis.event

import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot
import org.tgtravis.event.command.AddRepoCommand
import org.tgtravis.event.command.ClearReposCommand
import org.tgtravis.event.command.Command
import org.tgtravis.event.command.HelloCommand
import org.tgtravis.event.command.ListRepoCommand
import org.tgtravis.event.command.RemoveRepoCommand
import org.tgtravis.event.command.UnknownCommand

abstract class CommandEvent private constructor() {
    companion object {
        fun newEvent(bot: TravisBot, message: Message): Command {
            val text = message.text
            val command = text.substring(1, if (text.contains(' ')) text.indexOf(' ') else text.length)
            return when (command) {
                "start" -> HelloCommand(bot, message)
                "add" -> AddRepoCommand(bot, message)
                "list" -> ListRepoCommand(bot, message)
                "remove" -> RemoveRepoCommand(bot, message)
                "clear" -> ClearReposCommand(bot, message)
                else -> UnknownCommand(bot, message)
            }
        }
    }
}
