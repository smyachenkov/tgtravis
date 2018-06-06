package org.tgtravis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.tgtravis.configuration.TravisBotConfiguration
import org.tgtravis.event.CommandEvent

@Component
class TravisBot @Autowired constructor(private val config : TravisBotConfiguration) : TelegramLongPollingBot() {

    override fun getBotToken(): String = config.token

    override fun getBotUsername(): String = config.username

    override fun onUpdateReceived(update: Update) {
        if (update.message.isCommand) {
            CommandEvent.newEvent(this, update.message).process()
        }
    }
}
