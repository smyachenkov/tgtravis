package org.tgtravis.event.command

import org.mockito.Mockito.anyString
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import kotlin.test.Test

class UnknownCommandTest : AbstractCommandTest() {

    @Test
    fun respondsToUnknownCommand() {
        mockMessage("unknown")
        val command = spy(UnknownCommand(bot, message))
        command.process()
        verify(command).respond(anyString())
    }

    @Test
    fun respondsToUnknownCommandWithParams() {
        mockMessage("unknown param1 param2")
        val command = spy(UnknownCommand(bot, message))
        command.process()
        verify(command).respond(anyString())
    }
}
