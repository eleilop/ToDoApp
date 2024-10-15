package com.turing.alan.cpifp.data.source.remote

interface RemoteTaskDataSource {

    suspend fun readAll():List<RemoteTask>
    suspend fun saveTasks(tasks:List<RemoteTask>)

}