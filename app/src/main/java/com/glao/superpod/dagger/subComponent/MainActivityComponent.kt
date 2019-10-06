package com.glao.superpod.dagger.subComponent

import com.glao.superpod.dagger.scope.MainActivityScope
import com.glao.superpod.dagger.subModule.MainActivityModule
import com.glao.superpod.mvvm.activity.mainActivity.MainActivity
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(target: MainActivity)
}