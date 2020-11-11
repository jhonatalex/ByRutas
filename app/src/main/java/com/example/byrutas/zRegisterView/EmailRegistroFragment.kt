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
import kotlinx.android.synthetic.main.fragment_email_registro.*

class EmailRegistroFragment : Fragment() {

    val myViewModel: viewModelRutas by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        next_email.setOnClickListener {
            if ( editTextPersonEmail.text.isEmpty() ) {
                Toast.makeText(context, "Rellene el Campo Nombre ", Toast.LENGTH_LONG).show()

            } else {

                myViewModel.addEmail("${editTextPersonEmail.text}")

                findNavController().navigate(R.id.action_emailRegistroFragment_to_passwordRegistroFragment)


            }




        }




    }


}