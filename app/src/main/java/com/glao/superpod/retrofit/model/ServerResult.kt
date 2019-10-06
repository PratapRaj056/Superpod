package com.glao.superpod.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerResult(
    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("error")
    @Expose
    val error: ArrayList<String>? = null,

    @SerializedName("data")
    @Expose
    val data: String? = null
)