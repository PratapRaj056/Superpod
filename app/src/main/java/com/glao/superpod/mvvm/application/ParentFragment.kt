package com.glao.superpod.mvvm.application

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.glao.superpod.dagger.component.DaggerFragmentComponent
import com.glao.superpod.dagger.component.FragmentComponent
import com.glao.superpod.dagger.module.FragmentModule
import com.glao.superpod.utility.Session
import com.glao.superpod.utility.Utils
import javax.inject.Inject

abstract class ParentFragment : Fragment() {

    private val fragmentComponent: FragmentComponent by lazy {
        DaggerFragmentComponent
            .builder()
            .fragmentModule(FragmentModule(this))
            .build()
    }

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var utils: Utils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentComponent.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}