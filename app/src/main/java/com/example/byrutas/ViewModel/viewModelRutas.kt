package com.example.byrutas.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.byrutas.model.RepositoryApp
import com.example.byrutas.model.local.Athlete
import com.example.byrutas.model.local.DataEntity
import com.example.byrutas.model.local.RutasDataBase
import com.example.byrutas.model.remote.pojo.Main

class viewModelRutas (application:Application):AndroidViewModel(application){


    private val mRepository:RepositoryApp

    //val UserLiveData: LiveData<List<Athlete>>

    init {
        val mDao= RutasDataBase.getDatabase(application).daoRutas()
        mRepository= RepositoryApp(mDao)

    }

    fun requestData(city:String):LiveData<List<DataEntity>>{
        mRepository.getDataFromApi(city)
    return  mRepository.getDataTheDataBase()
    }



}