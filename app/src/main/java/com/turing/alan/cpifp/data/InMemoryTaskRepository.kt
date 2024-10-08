package com.turing.alan.cpifp.data

import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryTaskRepository @Inject constructor():TaskRepository {

    private val _tasks = mutableListOf<Task>()

    override fun create(title:String,body:String): Task {
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

    override fun update(task: Task): Task {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override fun readOne(id: String): Task {
        return _tasks.single { it.id == id }
    }

    override fun readAll() = _tasks.toList()


}