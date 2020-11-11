package com.example.byrutas.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.byrutas.model.RepositoryApp
import com.example.byrutas.model.local.Athlete
import com.example.byrutas.model.local.DataEntity
import com.example.byrutas.model.local.RutasDataBase
import com.example.byrutas.model.remote.pojo.Main
import kotlin.properties.Delegates

class viewModelRutas (application:Application):AndroidViewModel(application) {


    private val mRepository: RepositoryApp
    private  lateinit var nameString:String
    private  lateinit var emailString:String
    private  lateinit var passString:String
    private var weightDouble by Delegates.notNull<Double>()
    private var sexChar by Delegates.notNull<Char>()


    //val UserLiveData: LiveData<List<Athlete>>

    init {
        val mDao = RutasDataBase.getDatabase(application).daoRutas()
        mRepository = RepositoryApp(mDao)
    }


    fun loginValidate(username: String, password: String):LiveData<Athlete> {
       return mRepository.login(username, password)

    }



    fun requestData(): LiveData<List<DataEntity>> {
        mRepository.getDataFromApi()
        return mRepository.getDataTheDataBase()
    }


    fun addName(name: String)   { nameString=name   }
    fun addEmail(email: String) { emailString=email }
    fun addPass(pass: String)   {  passString=pass  }
    fun addWeight(peso: Double) { weightDouble=peso }

    fun addSex(sex: Char) {

        sexChar=sex
        Log.e("NOMBRE",emailString)
        Log.e("SEXO",sexChar.toString())
        Log.e("EMAIL",nameString.toString())


        mRepository.insertContact(Athlete(name = nameString,email = emailString,password = passString,
                                    weight = weightDouble, gender = sexChar))


    }








    }


