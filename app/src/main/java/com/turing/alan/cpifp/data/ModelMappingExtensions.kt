package com.turing.alan.cpifp.data

import com.turing.alan.cpifp.data.source.local.LocalTask
import com.turing.alan.cpifp.data.source.remote.RemoteTask
import java.time.Instant


fun RemoteTask.toLocal():LocalTask = LocalTask(
    this.id,
    this.title,
    this.body,
    this.completed,
    Instant.parse(this.createdAt)
)

fun List<RemoteTask>.toLocal():List<LocalTask> = map(RemoteTask::toLocal)

fun LocalTask.toExternal():Task = Task(
    this.id,
    this.title,
    this.body,
    this.completed,
    this.createdAt,
)

fun List<LocalTask>.toExternal():List<Task> = map(LocalTask::toExternal)

fun Task.toLocal():LocalTask {
    return LocalTask(
        this.id,
        this.title,
        this.body,
        this.completed,
        this.createdAt
    )
}

fun LocalTask.toRemote():RemoteTask {
    return RemoteTask(
        this.id,
        this.title,
        this.body,
        this.completed,
        this.createdAt.toString()
    )
}

fun List<LocalTask>.toRemote():List<RemoteTask> = map(LocalTask::toRemote)