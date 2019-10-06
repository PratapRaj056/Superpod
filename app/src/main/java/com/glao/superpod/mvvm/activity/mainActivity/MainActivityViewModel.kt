package com.glao.superpod.mvvm.activity.mainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glao.superpod.retrofit.model.ServerResult
import com.glao.superpod.utility.ApiType
import com.glao.superpod.utility.Response
import com.glao.superpod.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val response: MutableLiveData<Response> = MutableLiveData()

    fun addToken(u_id: String, token: String, fcm_token: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.addToken(u_id, token, u_id, fcm_token, "Android")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                response.value = Response.loading(ApiType.ADD_TOKEN)
            }
            .subscribe(
                { t: ServerResult -> response.value = Response.success(ApiType.ADD_TOKEN, t.status!!, t) },
                { t: Throwable? -> response.value = Response.error(ApiType.ADD_TOKEN, t!!) }
            ))
    }

    override fun onCleared() {
        super.onCleared()
//        compositeDisposable.dispose()
    }
}