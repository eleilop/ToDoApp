package com.turing.alan.cpifp.data.source.remote

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeRemoteDataSource @Inject constructor():RemoteTaskDataSource {
    private var tasks = mutableListOf<RemoteTask>()
    override suspend fun readAll(): List<RemoteTask> {
        delay(2000L)
        return tasks.toList()

    }

    override suspend fun saveTasks(newTasks: List<RemoteTask>) {
        delay(2000L)
        tasks = newTasks.toMutableList()
    }
}