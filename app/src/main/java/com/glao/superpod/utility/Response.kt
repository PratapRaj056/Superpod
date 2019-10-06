package com.glao.superpod.utility

import android.util.Log
import androidx.annotation.NonNull
import retrofit2.HttpException

/**
 * Classic Response Class for Any API Call,
 * It also helps in automatic logging
 * @param status Status LOADING, SUCCESS, ERROR
 * @param apiType ApiType ApiType of API Call (ENUM VALUE)
 * @param data Result Status success or failure message (in case of SUCCESS)
 * @param error Error (in case of ERROR)
 * @param result Result from the API call default null
 *
 * @author Pratap Raj Srivastava
 */
class Response private constructor(val status: Status, val apiType: ApiType, val data: String?, val error: Throwable?, val result: Any?) {

    companion object {
        private const val TAG = "API RESPONSE"

        fun loading(apiType: ApiType, result: Any? = null): Response {
            //Crashlytics.log(Log.DEBUG, TAG, "${Status.LOADING.name} -> ${apiType.name}")
            return Response(Status.LOADING, apiType, null, null, result)
        }

        fun success(apiType: ApiType, @NonNull data: String, result: Any? = null): Response {
            //Crashlytics.log(Log.DEBUG, TAG, "${Status.SUCCESS.name} -> ${apiType.name} -> $data")
            return Response(Status.SUCCESS, apiType, data, null, result)
        }

        fun error(apiType: ApiType, @NonNull error: Throwable, result: Any? = null): Response {
            //Crashlytics.log(Log.DEBUG, TAG, "${Status.ERROR.name} -> ${apiType.name} -> ${error.localizedMessage}")
            //Crashlytics.logException(error)
            if (error is HttpException) {
                val code = error.code()
                //Crashlytics.log(Log.DEBUG, TAG, "HTTP Response Code: $code")
            }

            return Response(Status.ERROR, apiType, null, error, result)
        }
    }
}