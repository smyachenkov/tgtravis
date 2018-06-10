package org.tgtravis.event.noification

import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.tgtravis.TravisBot
import org.tgtravis.controller.TravisNotification
import org.tgtravis.controller.TravisNotificationPayload
import org.tgtravis.service.TravisBotService

abstract class AbstractNotificationTest {

    val service = Mockito.mock(TravisBotService::class.java)
    val bot = Mockito.mock(TravisBot::class.java)
    val notification = Mockito.mock(TravisNotification::class.java)
    val payload = Mockito.mock(TravisNotificationPayload::class.java)

    init {
        `when`(bot.service).thenReturn(service)
        `when`(notification.payload).thenReturn(payload)
    }
}
