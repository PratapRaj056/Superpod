package com.glao.superpod.mvvm.activity.mainActivity

import android.os.Bundle
import com.glao.superpod.R
import com.glao.superpod.mvvm.application.ParentActivity

class MainActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityComponent.inject(this);
    }
}
