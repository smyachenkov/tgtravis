package org.tgtravis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class TgTravis

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    runApplication<TgTravis>(*args)
}
