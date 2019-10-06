package com.glao.superpod.dagger.subModule

import androidx.lifecycle.ViewModelProviders
import com.glao.superpod.dagger.scope.MainActivityScope
import com.glao.superpod.mvvm.activity.mainActivity.MainActivityViewModel
import com.glao.superpod.mvvm.activity.mainActivity.MainActivityViewModelFactory
import com.glao.superpod.mvvm.application.ParentActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun providesMainActivityViewModelFactory(): MainActivityViewModelFactory = MainActivityViewModelFactory()

    @Provides
    @MainActivityScope
    fun providesMainActivityViewModel(parentActivity: ParentActivity, acknowledgementViewModelFactory: MainActivityViewModelFactory): MainActivityViewModel =
        ViewModelProviders.of(parentActivity, acknowledgementViewModelFactory).get(MainActivityViewModel::class.java)
}