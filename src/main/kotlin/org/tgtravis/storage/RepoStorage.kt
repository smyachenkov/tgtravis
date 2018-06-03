package org.tgtravis.storage

import org.iq80.leveldb.Options
import org.iq80.leveldb.impl.Iq80DBFactory.*
import java.io.File
import java.nio.ByteBuffer
import java.nio.charset.Charset

object RepoStorage {

    private val db = factory.open(File("tgtravisdb"), Options())

    fun get(userId: Int): List<String> {
        val repos = db.get(userId.asBytes())?.toString(Charset.defaultCharset())
        return repos?.split(",").orEmpty()
    }

    fun add(userId: Int, repoIds: List<String>) {
        val repos = HashSet(get(userId))
        repos.addAll(repoIds)
        db.put(userId.asBytes(), repos.asBytes())
    }

    fun remove(userId: Int, repoIds: List<String>) {
        val current = get(userId)
        if (current.size == repoIds.size && current.containsAll(repoIds)) {
            db.delete(userId.asBytes())
        } else {
            db.put(userId.asBytes(), current.filter { !repoIds.contains(it) }.asBytes())
        }
    }

    fun clear(userId: Int) {
        db.delete(userId.asBytes())
    }

    private fun Int.asBytes(): ByteArray {
        return ByteBuffer.allocate(4).putInt(this).array()
    }

    private fun Collection<String>.asBytes(): ByteArray {
        return bytes(this.joinToString(","))
    }
}
