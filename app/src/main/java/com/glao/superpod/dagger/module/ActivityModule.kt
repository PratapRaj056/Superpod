package com.glao.superpod.dagger.module

import android.content.Context
import com.glao.superpod.mvvm.application.ParentActivity
import com.glao.superpod.utility.Session
import com.glao.superpod.utility.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val parentActivity: ParentActivity) {

    @Provides
    @Singleton
    fun provideContext(): Context = parentActivity

    @Provides
    @Singleton
    fun provideSession(context: Context): Session = Session(context)

    @Provides
    @Singleton
    fun provideUtil(context: Context): Utils = Utils.provideUtil(context)

    @Provides
    @Singleton
    fun provideParentActivity(): ParentActivity = parentActivity
}