package org.tgtravis.repository

import org.springframework.data.repository.CrudRepository
import org.tgtravis.model.Repo
import java.util.Optional

interface RepoRepository : CrudRepository<Repo, Long> {

    fun findByName(name: String): Optional<Repo>

}
