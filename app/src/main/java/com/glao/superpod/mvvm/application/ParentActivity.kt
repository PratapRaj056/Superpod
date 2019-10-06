package com.glao.superpod.mvvm.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glao.superpod.dagger.component.ActivityComponent
import com.glao.superpod.dagger.component.DaggerActivityComponent
import com.glao.superpod.dagger.module.ActivityModule
import com.glao.superpod.dagger.subComponent.MainActivityComponent
import com.glao.superpod.dagger.subModule.MainActivityModule
import com.glao.superpod.utility.Session
import com.glao.superpod.utility.Utils
import javax.inject.Inject

abstract class ParentActivity : AppCompatActivity() {


    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .build()
    }

    val mainActivityComponent: MainActivityComponent by lazy {
        activityComponent.plusMainActivityComponent(MainActivityModule())
    }

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var utils: Utils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}