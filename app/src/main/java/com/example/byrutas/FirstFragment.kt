package com.example.byrutas

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.byrutas.ViewModel.viewModelRutas
import com.example.byrutas.model.remote.ApiInterface
import com.example.byrutas.model.remote.RetrofitApiClient
import com.example.byrutas.model.remote.pojo.Example
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment() {

    private val ViewModel: viewModelRutas by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*
        ViewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("LISTA", it.toString())
        })

*/

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)


        }

        view.obtener.setOnClickListener {

            val city =TextCyty.text

            ViewModel.requestData("$city").observe(viewLifecycleOwner, Observer {
                Log.d("IMAGES", it.toString())

               // textView_temp.text= it[0].temp.toString()

            })


            //getWeatherDataSample("$city")
        }






    }

    fun getWeatherDataSample(name: String){

        val apiInterface: ApiInterface = RetrofitApiClient.retrofitInstance()

        val call: Call<Example> = apiInterface.getWeatherDataPrueba(name)

        call.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {

              //  Log.e("DATA", response.body()?.main.main.temp.toString())
               //textView_temp.text= response.body()?.main.main.toString())
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })



    }





}