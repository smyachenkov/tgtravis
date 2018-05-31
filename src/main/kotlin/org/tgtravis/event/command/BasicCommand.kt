package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender

abstract class BasicCommand(val bot: AbsSender,
                            val message: Message,
                            protected val command: String) : Command {

    protected val params : List<String> = retrieveParams()

    private fun retrieveParams(): List<String> {
        val prefix = "/$command"
        return if (prefix == message.text)
            emptyList()
        else
            message.text.removePrefix(prefix)
                    .trim()
                    .replace("\\s+".toRegex()," ")
                    .split(" ")
    }
}
