package org.tgtravis.event.command

import org.mockito.Mockito.*
import kotlin.test.Test

class HelloCommandTest : AbstractCommandTest() {

    @Test
    fun respondsWithSuccess() {
        mockMessage("start")
        val command = spy(HelloCommand(bot, message))
        command.process()
        verify(command).respond(anyString())
    }
}
