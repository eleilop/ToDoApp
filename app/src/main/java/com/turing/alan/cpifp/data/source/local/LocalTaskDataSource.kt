package com.turing.alan.cpifp.data.source.local

import kotlinx.coroutines.flow.Flow


interface LocalTaskDataSource {

    suspend fun create(localTask:LocalTask): LocalTask
    suspend fun createAll(localTasks: List<LocalTask>):List<LocalTask>
    suspend fun update(task: LocalTask): LocalTask
    suspend fun delete(id:String)
    suspend fun readOne(id:String): LocalTask
    suspend fun readAll():List<LocalTask>
    suspend fun deleteAll()
    fun getStream(): Flow<List<LocalTask>>
}