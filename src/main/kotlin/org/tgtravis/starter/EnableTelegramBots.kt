package org.tgtravis.starter

import org.springframework.context.annotation.Import

/*
    TODO: remove and use telegrambots-spring-boot-starter when https://github.com/rubenlagus/TelegramBots/issues/465 is fixed
 */

@Import(TelegramBotStarterConfiguration::class)
annotation class EnableTelegramBots
