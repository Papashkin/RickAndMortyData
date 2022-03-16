package com.antsfamily.domain.di

import com.antsfamily.domain.remote.RemoteSource
import com.antsfamily.domain.remote.RemoteSourceImpl
import com.antsfamily.domain.repositories.DataRepository
import com.antsfamily.domain.repositories.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindsDataRepository(repositoryImpl: DataRepositoryImpl): DataRepository

    @Binds
    abstract fun bindsRemoteSource(sourceImpl: RemoteSourceImpl): RemoteSource
}
