package com.turing.alan.cpifp.data

import java.time.Instant

data class Task(
    val id: Int,
    var title: String,
    var body: String,
    var completed: Boolean,
    val createdAt: Instant
)
