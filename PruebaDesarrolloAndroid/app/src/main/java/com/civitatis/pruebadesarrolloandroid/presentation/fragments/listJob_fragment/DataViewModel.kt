package com.civitatis.pruebadesarrolloandroid.presentation.fragments.listJob_fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.civitatis.pruebadesarrolloandroid.domain.Resource
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import com.civitatis.pruebadesarrolloandroid.domain.repository.InfoDataSource
import com.civitatis.pruebadesarrolloandroid.domain.repository.InfoRepository
import com.civitatis.pruebadesarrolloandroid.domain.usercase.GetInfo
import com.civitatis.pruebadesarrolloandroid.network.InfoDataSourceImpl
import com.civitatis.pruebadesarrolloandroid.network.RetrofitProvider
import com.civitatis.pruebadesarrolloandroid.utlis.Logger

class DataViewModel : ViewModel(), Logger{

    override val nameClass: String get() = "--->"+javaClass.simpleName
    private var infoDataSource: InfoDataSource = InfoDataSourceImpl(RetrofitProvider())
    private var getInfo = GetInfo(InfoRepository(infoDataSource))

    var myDataLiveData = MutableLiveData<Resource<ArrayList<MyDataResponse>>>()


    fun getData(){
        myDataLiveData.value = Resource.Loading()

        getInfo.invoke(onResult = { it ->
            val listtemp = it.data?.sortedBy { it.created_at }
            myDataLiveData.value = Resource.Success( ArrayList(listtemp) ?: arrayListOf())
        }, onError = {
            Log.d("","")
        })
    }
}