package com.civitatis.pruebadesarrolloandroid.domain.repository

import com.civitatis.pruebadesarrolloandroid.domain.Resource
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse

interface InfoDataSource {
    suspend fun getInfo(): Resource<ArrayList<MyDataResponse>>
}