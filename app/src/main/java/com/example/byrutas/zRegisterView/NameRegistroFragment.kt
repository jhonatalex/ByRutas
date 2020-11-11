package com.example.byrutas.zRegisterView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.byrutas.R
import com.example.byrutas.ViewModel.viewModelRutas
import kotlinx.android.synthetic.main.fragment_name_registro.*

class NameRegistroFragment : Fragment() {


    val myViewModel: viewModelRutas by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_registro, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        next_name.setOnClickListener {

            if ( editTextPersonName.text.isEmpty() ) {
                Toast.makeText(context, "Rellene el Campo Nombre ", Toast.LENGTH_LONG).show()

            } else {

                myViewModel.addName("${editTextPersonName.text}")

                findNavController().navigate(R.id.action_nameRegistroFragment_to_emailRegistroFragment)


            }





        }


    }






}
