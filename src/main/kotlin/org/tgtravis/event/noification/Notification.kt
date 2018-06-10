package org.tgtravis.event.noification

import org.tgtravis.event.Event
import org.tgtravis.model.User

interface Notification : Event {
    fun notifyUsers(users: Set<User>, message: String)
}
