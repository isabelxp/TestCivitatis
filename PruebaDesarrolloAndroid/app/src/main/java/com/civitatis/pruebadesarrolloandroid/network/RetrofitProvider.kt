package com.civitatis.pruebadesarrolloandroid.network

import com.civitatis.pruebadesarrolloandroid.domain.Resource
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import com.civitatis.pruebadesarrolloandroid.network.apiservice.DataApi
import com.civitatis.pruebadesarrolloandroid.utlis.Logger
import com.civitatis.pruebadesarrolloandroid.utlis.RestApi
import retrofit2.awaitResponse

class RetrofitProvider : Logger {

    override val nameClass: String get() = "--->" + javaClass.simpleName
    private val retrofit by lazy { RestApi.ServiceBuilder.buildService(DataApi::class.java) }
    suspend fun getData(): Resource<ArrayList<MyDataResponse>> {

        val data = retrofit.getData().awaitResponse()
        return if (data.isSuccessful) {
            Resource.Success(data.body()!!)
        } else {
            Resource.Error(Throwable(""))
        }
    }
}