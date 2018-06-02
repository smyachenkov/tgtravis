package org.tgtravis.event.command

import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BasicCommandTest {

    private class TestCommand(bot: AbsSender,
                              message: Message) : BasicCommand(bot, message, "test") {

        override fun process() {}

        fun checkParams() : List<String> {
            return params
        }
    }

    @Test
    fun returnsEmptyListIfParamsAreAbsent() {
        val bot = mock(AbsSender::class.java)
        val message = mock(Message::class.java)
        `when`(message.text).thenReturn("/test")
        val command = TestCommand(bot, message)
        assertNotNull(command.checkParams(), "Params list must be present for empty message body")
        assertEquals(0, command.checkParams().size, "Params list must be empty for empty message body")
    }

    @Test
    fun parsesSingleParam() {
        val bot = mock(AbsSender::class.java)
        val message = mock(Message::class.java)
        `when`(message.text).thenReturn("/test hello")
        val command = TestCommand(bot, message)
        assertNotNull(command.checkParams(), "Params list must be present for single param message body")
        assertEquals(1, command.checkParams().size, "Params list must contain exactly one item for single param message body")
    }

    @Test
    fun parsesMultipleParams() {
        val bot = mock(AbsSender::class.java)
        val message = mock(Message::class.java)
        `when`(message.text).thenReturn("/test 1 2 3")
        val command = TestCommand(bot, message)
        assertNotNull(command.checkParams(), "Params list must be present for multiple params message body")
        assertEquals(3, command.checkParams().size, "Params list must contain all params from message body")
    }

    @Test
    fun ignoresMultipleSpacesAsParamSeparators() {
        val bot = mock(AbsSender::class.java)
        val message = mock(Message::class.java)
        `when`(message.text).thenReturn("/test hello   world    how    are    you   ")
        val command = TestCommand(bot, message)
        assertNotNull(command.checkParams(), "Params list must be present for multiple params message body")
        assertEquals(5, command.checkParams().size, "Params list must contain all params from message body")
    }
}
