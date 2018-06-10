package org.tgtravis.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.tgtravis.TravisBot
import org.tgtravis.event.noification.BuildNotification
import java.net.URLDecoder

@RestController
class NotificationController

@Autowired constructor(private val bot: TravisBot) {

    @PostMapping("/notifications")
    fun processNotification(
        @RequestParam("payload") payload: String,
        @RequestHeader("travis-repo-slug") repo: String,
        @RequestHeader("signature") signature: String
    ) {
        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())
        val jsonPayload = mapper.readValue(URLDecoder.decode(payload, "UTF-8"),
                TravisNotificationPayload::class.java)
        val notification = TravisNotification(repo, signature, jsonPayload)
        BuildNotification(bot, notification).process()
    }
}
