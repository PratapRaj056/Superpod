package com.glao.superpod.dagger.component

import com.glao.superpod.dagger.module.ActivityModule
import com.glao.superpod.dagger.subComponent.MainActivityComponent
import com.glao.superpod.dagger.subModule.MainActivityModule
import com.glao.superpod.mvvm.application.ParentActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(target: ParentActivity)

    fun plusMainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent
}