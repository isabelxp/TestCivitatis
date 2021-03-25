package com.civitatis.pruebadesarrolloandroid.domain.usercase

import com.civitatis.pruebadesarrolloandroid.domain.Resource
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import com.civitatis.pruebadesarrolloandroid.domain.repository.InfoRepository

class GetInfo(private val infoRepository: InfoRepository) :
    BaseUseCase<Resource<ArrayList<MyDataResponse>>, Any>() {

    override suspend fun run(params: Any?): Resource<ArrayList<MyDataResponse>> {
        return infoRepository.getInfo()
    }
}