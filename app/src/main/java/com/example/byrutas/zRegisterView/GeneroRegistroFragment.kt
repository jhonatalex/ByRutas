package com.example.byrutas.zRegisterView

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.byrutas.R
import com.example.byrutas.ViewModel.viewModelRutas
import kotlinx.android.synthetic.main.fragment_genero_registro.*

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class GeneroRegistroFragment : Fragment() {

    val myViewModel: viewModelRutas by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genero_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_sex.setOnClickListener {
            var gender='F'
            if (radioMen.isChecked) {
            gender='M' } else { gender='F' }

            if(radioMen.isChecked || radioGirls.isChecked){

                myViewModel.addSex(gender)

                val toast=Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 40, 750)
                toast.show()

                findNavController().navigate(R.id.action_generoRegistroFragment_to_SecondFragment2)

            }else{

                Toast.makeText(context,"Selecione 1 ",Toast.LENGTH_LONG).show()
            }



        }




    }


}