package org.tgtravis.event.command

interface Command {
    fun process()
    fun respond(text: String)
}
