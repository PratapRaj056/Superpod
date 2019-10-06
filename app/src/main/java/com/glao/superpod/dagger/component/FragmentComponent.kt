package com.glao.superpod.dagger.component

import com.glao.superpod.dagger.module.FragmentModule
import com.glao.superpod.mvvm.application.ParentFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(target: ParentFragment)
}