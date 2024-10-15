package com.turing.alan.cpifp.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun create(title:String,body:String):Task
    suspend fun update(task:Task):Task
    suspend fun delete(id:String)
    suspend fun readOne(id:String):Task
    suspend fun readAll():List<Task>

    suspend fun getStream(): Flow<List<Task>>

}