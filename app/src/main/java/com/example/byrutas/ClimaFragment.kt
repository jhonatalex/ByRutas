package com.example.byrutas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.byrutas.ViewModel.viewModelRutas
import com.example.byrutas.model.remote.ApiInterface
import com.example.byrutas.model.remote.RetrofitApiClient
import com.example.byrutas.model.remote.pojo.Example
import kotlinx.android.synthetic.main.fragment_clima.*
import kotlinx.android.synthetic.main.fragment_clima.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClimaFragment : Fragment() {

    private val ViewModel: viewModelRutas by activityViewModels()
    private var ultimo=0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clima, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            startActivity(intent)


            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }


            ViewModel.requestData().observe(viewLifecycleOwner, Observer {


                if (it.isNotEmpty()){

                    ultimo = it.size-1
                    textView_temp.text = it[ultimo].temp.toString()
                    text_ciudad.text = it[ultimo].city
                    text_descripcion.text = it[ultimo].cloudsDescription
                    text_humeda.text = it[ultimo].humidity.toString()
                    text_sensacion.text = it[ultimo].feelsLike.toString()
                    text_pactmosferica.text = it[ultimo].pressure.toString()
                    text_temmax.text = it[ultimo].tempMax.toString()
                    text_tempmin.text = it[ultimo].tempMin.toString()
                    text_nubosidad.text = it[ultimo].clouds.toString()

                }



            })


            //getWeatherDataSample("$city")





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