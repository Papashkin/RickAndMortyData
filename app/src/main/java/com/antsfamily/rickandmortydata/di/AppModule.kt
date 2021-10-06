package com.antsfamily.rickandmortydata.di

import com.antsfamily.rickandmortydata.data.remote.RickMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRickMortyService(okHttpClient: OkHttpClient): RickMortyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(RickMortyService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RickMortyService::class.java)
    }
}
