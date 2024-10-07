package com.turing.alan.cpifp.data

import java.time.Instant

class InMemoryTaskRepository private constructor():TaskRepository {

    companion object {
        private var instance: InMemoryTaskRepository? = null
        fun getInstance():InMemoryTaskRepository {

            if (instance == null) {
                instance = InMemoryTaskRepository()
            }
            return instance!!

        }
    }
    private val _tasks = mutableListOf<Task>()



    override fun create(task: Task): Task {
        val id = if (_tasks.size==0) 1 else _tasks.last().id+1
        val newTask = Task(id,
            task.title,
            task.body,
            task.completed,
            Instant.now(),
            )
        _tasks.add(newTask)
        return  newTask
    }

    override fun update(task: Task): Task {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun readOne(id: Int): Task {
        return _tasks.single { it.id == id }
    }

    override fun readAll() = _tasks.toList()


}