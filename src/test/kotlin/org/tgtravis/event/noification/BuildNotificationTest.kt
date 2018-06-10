package org.tgtravis.event.noification

import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.eq
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.tgtravis.model.User
import kotlin.test.Test

class BuildNotificationTest : AbstractNotificationTest() {

    @Test
    fun sendsMessageToEmptySetOfWatchers() {
        `when`(notification.repoName).thenReturn("repo")
        `when`(service.retrieveUsers("repo")).thenReturn(emptySet())
        val buildNotification = spy(BuildNotification(bot, notification))
        buildNotification.process()
        verify(buildNotification).notifyUsers(eq(emptySet()) ?: emptySet(), anyString())
    }

    @Test
    fun sendsMessageToSetOfUsers() {
        val users = setOf(User(1), User(2))
        `when`(notification.repoName).thenReturn("repo")
        `when`(service.retrieveUsers("repo")).thenReturn(users)
        val buildNotification = spy(BuildNotification(bot, notification))
        buildNotification.process()
        verify(buildNotification).notifyUsers(eq(users) ?: users, anyString())
    }
}
