package com.turing.alan.cpifp.data

import com.turing.alan.cpifp.data.source.local.LocalTaskDataSource
import com.turing.alan.cpifp.data.source.remote.RemoteTaskDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTaskRepository @Inject constructor(
    private val remoteTaskDataSource: RemoteTaskDataSource,
    private val localTaskDataSource: LocalTaskDataSource
) :TaskRepository {

    override suspend fun create(title: String,
                                body: String,): Task {

        val taskId = UUID.randomUUID().toString()

        val task = Task(taskId,title,body,false, Instant.now())

        localTaskDataSource.create(task.toLocal())
        saveTasksToNetwork()
        return task

    }

    private suspend fun saveTasksToNetwork() {
        withContext(Dispatchers.IO) {
            val localTasks = localTaskDataSource.readAll()
            val remoteTasks = localTasks.toRemote()
            remoteTaskDataSource.saveTasks(remoteTasks)

        }
    }

    override suspend fun update(task: Task): Task {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readOne(id: String): Task {
        TODO("Not yet implemented")
    }

    override suspend fun readAll(): List<Task> {

        refreshTask()
        return withContext(Dispatchers.IO) {
            localTaskDataSource.readAll().toExternal()
        }
    }

    override suspend fun getStream(): Flow<List<Task>> {
        return localTaskDataSource.getStream().map {
            localTasks -> withContext(Dispatchers.IO) {localTasks.toExternal()}
        }
    }

    private suspend fun refreshTask() {
        withContext(Dispatchers.IO) {
            val remoteTasks = remoteTaskDataSource.readAll()
            localTaskDataSource.deleteAll()
            localTaskDataSource.createAll(remoteTasks.toLocal())
        }
    }
}