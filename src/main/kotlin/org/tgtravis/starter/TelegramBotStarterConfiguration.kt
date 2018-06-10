package org.tgtravis.starter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.generics.LongPollingBot
import org.telegram.telegrambots.generics.WebhookBot
import javax.annotation.PostConstruct

/*
    TODO: remove and use telegrambots-spring-boot-starter when https://github.com/rubenlagus/TelegramBots/issues/465 is fixed
 */

@Configuration
open class TelegramBotStarterConfiguration

@Autowired constructor(
    longPollingBots: List<LongPollingBot>?,
    webHookBots: List<WebhookBot>?
) {
    private val longPollingBots: List<LongPollingBot> = longPollingBots.orEmpty()
    private val webHookBots: List<WebhookBot> = webHookBots.orEmpty()

    @Autowired
    lateinit var telegramBotsApi: TelegramBotsApi

    @PostConstruct
    fun registerBots() {
        longPollingBots.forEach { telegramBotsApi.registerBot(it) }
        webHookBots.forEach { telegramBotsApi.registerBot(it) }
    }

    @Bean
    @ConditionalOnMissingBean(TelegramBotsApi::class)
    open fun telegramBotsApi(): TelegramBotsApi {
        return TelegramBotsApi()
    }
}
