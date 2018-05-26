package org.tgtravis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import java.io.File

class TravisBot(private val config: BotConfig): TelegramLongPollingBot() {

    override fun getBotToken(): String = config.token

    override fun getBotUsername(): String = config.username

    override fun onUpdateReceived(update: Update) {
        TODO("not implemented")
    }
}

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    val mapper = ObjectMapper(YAMLFactory())
    mapper.registerModule(KotlinModule())
    val config = mapper.readValue(File("config.yaml"), BotConfig::class.java)
    val api = TelegramBotsApi()
    api.registerBot(TravisBot(config))
}

data class BotConfig(val username: String, val token: String)
