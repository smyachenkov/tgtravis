package org.tgtravis.event.command

import org.mockito.Mockito.anyString
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import kotlin.test.Test

class AddRepoCommandTest : AbstractCommandTest() {

    @Test
    fun respondsWithSuccess() {
        mockMessage("add")
        val command = spy(AddRepoCommand(bot, message))
        command.process()
        verify(command).respond(anyString())
    }
}
