package org.tgtravis.event.command

import org.mockito.Mockito.anyString
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import kotlin.test.Test

class ClearReposCommandTest : AbstractCommandTest() {

    @Test
    fun respondsWithSuccess() {
        mockMessage("clear")
        val command = spy(ClearReposCommand(bot, message))
        command.process()
        verify(command).respond(anyString())
    }
}
