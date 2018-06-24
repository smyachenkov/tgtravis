package org.tgtravis.event.noification

import org.tgtravis.TravisBot
import org.tgtravis.controller.TravisNotification

class BuildNotification(
    bot: TravisBot,
    private val notification: TravisNotification
) : BasicNotification(bot) {

    private val messageTemplate = "New [build](%s) has arrived!\n\n" +
            "Repository: %s\n" +
            "Status: *%s*\n" +
            "Result: *%s*\n" +
            "Author: %s %s\n" +
            "Committer: %s %s\n" +
            "Committed at: *%s*\n" +
            "Started at: %s\n" +
            "Duration: %s\n" +
            "Finished at: %s\n"

    override fun process() {
        val users = bot.service.retrieveUsers(notification.repoName)
        notifyUsers(users, String.format(messageTemplate,
                notification.payload.build_url,
                notification.repoName,
                notification.payload.status_message,
                notification.payload.result_message,
                notification.payload.author_name,
                notification.payload.author_email,
                notification.payload.committer_name,
                notification.payload.committer_email,
                notification.payload.committed_at,
                notification.payload.started_at,
                notification.payload.duration,
                notification.payload.finished_at
            )
        )
    }
}
