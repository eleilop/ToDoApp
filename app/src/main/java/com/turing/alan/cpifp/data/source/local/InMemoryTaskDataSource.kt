package com.turing.alan.cpifp.data.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryTaskDataSource @Inject constructor():LocalTaskDataSource {

    private val _tasks = mutableListOf<LocalTask>()
    private val _tasksStream
    = MutableStateFlow<List<LocalTask>>(_tasks.toList())

    override suspend fun create(localTask: LocalTask):LocalTask {
        _tasks.add(localTask)
        _tasksStream.value = _tasks.toList()
        return localTask
    }

    override suspend fun createAll(localTasks: List<LocalTask>): List<LocalTask> {
        _tasks.addAll(localTasks)
        _tasksStream.value = _tasks.toList()
        return _tasks.toList()
    }

    override suspend fun update(task: LocalTask): LocalTask {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readOne(id: String): LocalTask {
        TODO("Not yet implemented")
    }

    override suspend fun readAll(): List<LocalTask> {
        return _tasks.toList()
    }

    override suspend fun deleteAll() {
        _tasks.clear()
    }

    override fun getStream(): Flow<List<LocalTask>> {
        return _tasksStream
    }
}