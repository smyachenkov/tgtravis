package org.tgtravis.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.tgtravis.starter.EnableTelegramBots

@EnableTelegramBots
@Configuration
@ConfigurationProperties(prefix = "bot")
open class TravisBotConfiguration {
    lateinit var username: String
    lateinit var token: String
}
