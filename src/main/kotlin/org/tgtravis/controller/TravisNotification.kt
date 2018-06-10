package org.tgtravis.controller

data class TravisNotification(
    val repoName: String,
    val signature: String,
    val payload: TravisNotificationPayload
)
