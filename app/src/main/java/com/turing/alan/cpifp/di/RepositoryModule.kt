package com.turing.alan.cpifp.di

import com.turing.alan.cpifp.data.DefaultTaskRepository
import com.turing.alan.cpifp.data.InMemoryTaskRepository
import com.turing.alan.cpifp.data.TaskRepository
import com.turing.alan.cpifp.data.source.local.InMemoryTaskDataSource
import com.turing.alan.cpifp.data.source.local.LocalTaskDataSource
import com.turing.alan.cpifp.data.source.remote.FakeRemoteDataSource
import com.turing.alan.cpifp.data.source.remote.RemoteTaskDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTaskRepository(defaultTaskRepository: DefaultTaskRepository):TaskRepository

    @Binds
    abstract fun bindLocalDataSource(localDataSource: InMemoryTaskDataSource):LocalTaskDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: FakeRemoteDataSource):RemoteTaskDataSource
}