package com.civitatis.pruebadesarrolloandroid.network.apiservice

import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface DataApi {
    @Headers("Content-Type: application/json")
    @GET("positions.json")
    fun getData(): Call<ArrayList<MyDataResponse>>
}