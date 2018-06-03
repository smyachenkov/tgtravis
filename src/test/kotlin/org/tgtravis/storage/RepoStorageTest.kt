package org.tgtravis.storage

import kotlin.test.*

class RepoStorageTest {

    private val user = 123

    @AfterTest
    fun clear() {
        RepoStorage.clear(user)
    }

    @Test
    fun getReturnsCorrectSingleEntryList() {
        RepoStorage.add(user, listOf("repo1"))
        val repos = RepoStorage.get(user)
        assertEquals(1, repos.size, "get must return list with single entry")
        assertEquals("repo1", repos[0], "get must not change entry value")
    }

    @Test
    fun getReturnsCorrectMultipleEntryList() {
        RepoStorage.add(user, listOf("repo1"))
        RepoStorage.add(user, listOf("repo2"))
        val repos = RepoStorage.get(user)
        assertEquals(2, repos.size, "get must return list with correct number of entries")
        assertTrue(repos.contains("repo1"), "get must not change entry values")
        assertTrue(repos.contains("repo2"), "get must not change entry values")
    }

    @Test
    fun getReturnsEmptyListIfNoEntry() {
        val repos = RepoStorage.get(user)
        assertNotNull(repos, "get must return an empty list")
        assertTrue(repos.isEmpty(), "get must return an empty list")
    }

    @Test
    fun addPutsReposForNewUser() {
        RepoStorage.add(user, listOf("repo1", "repo2"))
        val repos = RepoStorage.get(user)
        assertEquals(2, repos.size, "add must put all values for new key")
        assertTrue(repos.contains("repo1"), "add must not change entry values")
        assertTrue(repos.contains("repo2"), "add must not change entry values")
    }

    @Test
    fun addPutsReposForExistingUser() {
        RepoStorage.add(user, listOf("repo1", "repo2"))
        RepoStorage.add(user, listOf("repo3"))
        val repos = RepoStorage.get(user)
        assertEquals(3, repos.size, "add must put all values for old value")
        assertTrue(repos.contains("repo1"), "add must not change entry values")
        assertTrue(repos.contains("repo2"), "add must not change entry values")
        assertTrue(repos.contains("repo3"), "add must not change entry values")
    }

    @Test
    fun addIgnoresDuplicateValuesForSingleInsert() {
        RepoStorage.add(user, listOf("repo1", "repo1"))
        val repos = RepoStorage.get(user)
        assertEquals(1, repos.size, "add must ignore duplicate values")
        assertTrue(repos.contains("repo1"), "add must not change entry values")
    }

    @Test
    fun addIgnoresDuplicateValuesForMultipleInserts() {
        RepoStorage.add(user, listOf("repo1", "repo2"))
        RepoStorage.add(user, listOf("repo2", "repo3"))
        val repos = RepoStorage.get(user)
        assertEquals(3, repos.size, "add must ignore duplicate values")
        assertTrue(repos.contains("repo1"), "add must not change entry values")
        assertTrue(repos.contains("repo2"), "add must not change entry values")
        assertTrue(repos.contains("repo3"), "add must not change entry values")
    }

    @Test
    fun removeRemovesSingleValue() {
        RepoStorage.add(user, listOf("repo1", "repo2"))
        RepoStorage.remove(user, listOf("repo2"))
        val repos = RepoStorage.get(user)
        assertEquals(1, repos.size, "remove must delete single value")
        assertTrue(repos.contains("repo1"), "remove must not change entry values")
    }

    @Test
    fun removeRemovesMultipleValues() {
        RepoStorage.add(user, listOf("repo1", "repo2", "repo3"))
        RepoStorage.remove(user, listOf("repo2", "repo1"))
        val repos = RepoStorage.get(user)
        assertEquals(1, repos.size, "remove must delete multiple values")
        assertTrue(repos.contains("repo3"), "remove must not change entry values")
    }

    @Test
    fun removeRemovesAllValues() {
        RepoStorage.add(user, listOf("repo1", "repo2"))
        RepoStorage.remove(user, listOf("repo2", "repo1"))
        val repos = RepoStorage.get(user)
        assertEquals(0, repos.size, "remove must delete all values")
    }

    @Test
    fun clearRemovesMultipleValues() {
        RepoStorage.add(user, listOf("repo1", "repo2"))
        RepoStorage.clear(user)
        val repos = RepoStorage.get(user)
        assertEquals(0, repos.size, "clear must remove all values")
    }

    @Test
    fun clearIgnoresAbsentKey() {
        RepoStorage.clear(user + 1)
        val repos = RepoStorage.get(user + 1)
        assertEquals(0, repos.size, "clear must ignore absent key in db")
    }

}
