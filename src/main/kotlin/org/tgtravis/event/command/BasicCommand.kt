package org.tgtravis.event.command

import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.bots.AbsSender

abstract class BasicCommand(val bot : AbsSender, val message: Message) : Command
