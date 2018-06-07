package org.tgtravis.repository

import org.springframework.data.repository.CrudRepository
import org.tgtravis.model.Repo

interface RepoRepository : CrudRepository<Repo, Long>
