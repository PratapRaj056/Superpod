package com.glao.superpod.dagger.module

import android.content.Context
import com.glao.superpod.mvvm.application.ParentFragment
import com.glao.superpod.utility.Session
import com.glao.superpod.utility.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FragmentModule(private val parentFragment: ParentFragment) {

    @Provides
    @Singleton
    fun provideContext(): Context = parentFragment.context!!

    @Provides
    @Singleton
    fun provideSession(context: Context): Session = Session(context)

    @Provides
    @Singleton
    fun provideUtil(context: Context): Utils = Utils.provideUtil(context)

    @Provides
    @Singleton
    fun provideParentFragment(): ParentFragment = parentFragment
}