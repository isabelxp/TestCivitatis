package com.civitatis.pruebadesarrolloandroid.network

import com.civitatis.pruebadesarrolloandroid.domain.Resource
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import com.civitatis.pruebadesarrolloandroid.domain.repository.InfoDataSource

class InfoDataSourceImpl(private val retrofitProvider: RetrofitProvider) : InfoDataSource {

    override suspend fun getInfo(): Resource<ArrayList<MyDataResponse>> {
        return retrofitProvider.getData()
    }
}