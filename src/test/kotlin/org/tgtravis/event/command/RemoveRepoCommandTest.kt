package org.tgtravis.event.command

import org.mockito.Mockito
import org.mockito.Mockito.verify
import kotlin.test.Test

class RemoveRepoCommandTest : AbstractCommandTest() {

    @Test
    fun respondsWithSuccess() {
        mockMessage("remove")
        val command = Mockito.spy(RemoveRepoCommand(bot, message))
        command.process()
        verify(command).respond(Mockito.anyString())
    }
}
