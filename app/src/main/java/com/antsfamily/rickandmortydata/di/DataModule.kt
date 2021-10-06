package com.antsfamily.rickandmortydata.di

import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.data.DataRepositoryImpl
import com.antsfamily.rickandmortydata.data.remote.RemoteSource
import com.antsfamily.rickandmortydata.data.remote.RemoteSourceImpl
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
