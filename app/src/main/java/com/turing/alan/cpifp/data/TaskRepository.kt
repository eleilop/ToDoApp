package com.turing.alan.cpifp.data

interface TaskRepository {

    fun create(title:String,body:String):Task
    fun update(task:Task):Task
    fun delete(id:String)
    fun readOne(id:String):Task
    fun readAll():List<Task>
}