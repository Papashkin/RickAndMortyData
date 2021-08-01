package com.antsfamily.rickandmortydata.di

import androidx.lifecycle.ViewModel
import com.antsfamily.rickandmortydata.presentation.CharacterInfoViewModel
import com.antsfamily.rickandmortydata.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@InstallIn(SingletonComponent::class)
@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterInfoViewModel::class)
    abstract fun bindCharacterInfoViewModel(viewModel: CharacterInfoViewModel) : ViewModel
}