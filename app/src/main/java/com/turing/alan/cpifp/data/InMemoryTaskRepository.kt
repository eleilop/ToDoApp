package com.turing.alan.cpifp.data

import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton


// TODO
// ESTO CODIGO HA DEJADO DE USARSE
// Mirar Inyector de dependencias
@Singleton
class InMemoryTaskRepository @Inject constructor():TaskRepository {

    private val _tasks = mutableListOf<Task>()

    override suspend fun create(title:String,body:String): Task {
//        val id = if (_tasks.size==0) 1 else _tasks.last().id+1
        val id = UUID.randomUUID().toString()
        val newTask = Task(id,
            title,
            body,
            false,
            Instant.now(),
            )
        _tasks.add(newTask)
        return  newTask
    }

    override suspend fun update(task: Task): Task {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readOne(id: String): Task {
        return _tasks.single { it.id == id }
    }

    override suspend fun readAll() = _tasks.toList()
    override suspend fun getStream(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }


}