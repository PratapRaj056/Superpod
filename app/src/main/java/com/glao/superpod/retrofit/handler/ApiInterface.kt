package com.glao.superpod.retrofit.handler

import com.glao.superpod.retrofit.model.ServerResult
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("api/v1/users/{user_id}/token/")
    fun addToken(@Header("id") userId: String, @Header("token") token: String,
                 @Path("user_id") userID: String,
                 @Field("FCM_token") fcmToken: String,
                 @Field("device_type") device: String): Observable<ServerResult>

     @GET("api/v1/groups")
    fun getClassList(@Header("id") userId: String, @Header("token") token: String): Observable<ServerResult>

}