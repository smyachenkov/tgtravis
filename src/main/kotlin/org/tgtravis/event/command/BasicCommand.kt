package org.tgtravis.event.command

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.tgtravis.TravisBot
import org.tgtravis.model.User

abstract class BasicCommand(val bot: TravisBot, val message: Message, protected val command: String) : Command {

    protected val params: List<String> = retrieveParams()

    protected val user: User = bot.service.retrieveUser(message)

    private fun retrieveParams(): List<String> {
        val prefix = "/$command"
        return if (prefix == message.text)
            emptyList()
        else
            message.text.removePrefix(prefix)
                    .trim()
                    .replace("\\s+".toRegex(), " ")
                    .split(" ")
    }

    override fun respond(text: String) {
        val response = SendMessage(message.chatId, text)
        bot.execute(response)
    }
}
