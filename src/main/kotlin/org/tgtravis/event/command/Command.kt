package org.tgtravis.event.command

import org.tgtravis.event.Event

interface Command : Event {
    fun respond(text: String)
}
