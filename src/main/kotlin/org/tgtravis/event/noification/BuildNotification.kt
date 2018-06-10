package org.tgtravis.event.noification

import org.tgtravis.TravisBot
import org.tgtravis.controller.TravisNotification

class BuildNotification(
    bot: TravisBot,
    private val notification: TravisNotification
) : BasicNotification(bot) {

    override fun process() {
        val users = bot.service.retrieveUsers(notification.repoName)
        notifyUsers(users, "New build has arrived! ${notification.payload.build_url}")
    }
}
