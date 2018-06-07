package org.tgtravis.repository

import org.springframework.data.repository.CrudRepository
import org.tgtravis.model.User
import java.util.*

interface UserRepository : CrudRepository<User, Long> {

    fun findByTelegramId(telegramId : Long) : Optional<User>

}
