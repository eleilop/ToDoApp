package com.turing.alan.cpifp.data

interface TaskRepository {

    fun create(task:Task):Task
    fun update(task:Task):Task
    fun delete(id:Int)
    fun readOne(id:Int):Task
    fun readAll():List<Task>
}