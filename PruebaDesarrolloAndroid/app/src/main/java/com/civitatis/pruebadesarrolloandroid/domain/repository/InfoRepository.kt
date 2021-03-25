package com.civitatis.pruebadesarrolloandroid.domain.repository

import com.civitatis.pruebadesarrolloandroid.domain.Resource
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse

class InfoRepository(val infoDataSource: InfoDataSource) {
    suspend fun getInfo() : Resource<ArrayList<MyDataResponse>> = infoDataSource.getInfo()
}