package com.turing.alan.cpifp.data.source.remote

data class RemoteTask(
    val id:String,
    val title:String,
    val body:String,
    val completed: Boolean,
    val createdAt: String
)
