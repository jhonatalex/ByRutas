package com.example.byrutas.zRegisterView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.byrutas.R
import com.example.byrutas.ViewModel.viewModelRutas
import kotlinx.android.synthetic.main.fragment_weight_registro.*

class WeightRegistroFragment : Fragment() {

    val myViewModel: viewModelRutas by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weight_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_weidht.setOnClickListener {
            if ( editTextPersonWeight.text.isEmpty() ) {
                Toast.makeText(context, "Rellene el Campo Peso", Toast.LENGTH_LONG).show()

            } else {

                myViewModel.addWeight(editTextPersonWeight.text.toString().toDouble())

                findNavController().navigate(R.id.action_weightRegistroFragment_to_generoRegistroFragment)


            }



        }










    }

}