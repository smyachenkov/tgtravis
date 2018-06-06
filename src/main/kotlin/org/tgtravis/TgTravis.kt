package org.tgtravis

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
open class TgTravis

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    SpringApplication.run(TgTravis::class.java, *args)
}
