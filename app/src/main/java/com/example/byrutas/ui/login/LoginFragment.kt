package com.example.byrutas.ui.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.byrutas.MapsActivity

import com.example.byrutas.R
import com.example.byrutas.RutasActivity
import com.example.byrutas.ViewModel.viewModelRutas
import com.example.byrutas.model.local.Athlete
import com.google.android.gms.maps.MapsInitializer
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    val loginViewModel: viewModelRutas by activityViewModels()
    lateinit var Token:Athlete

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)




        loginButton.setOnClickListener {

            if (usernameEditText.text.isEmpty() || passwordEditText.text.isEmpty()) {
                Toast.makeText(context, "Rellene Todos Los campos ", Toast.LENGTH_LONG).show()

            } else {

                loginViewModel.loginValidate(usernameEditText.text.toString(), passwordEditText.text.toString())
                .observe(viewLifecycleOwner, Observer {
                            loadingProgressBar.visibility = View.GONE

                                if (it==null) {
                                    Toast.makeText(context, "DATOS INCORRECTOS ", Toast.LENGTH_LONG).show()
                                } else {

                                    Token=it
                                    //findNavController().navigate(R.id.action_loginFragment_to_mapsActivity)
                                    val intent = Intent(context, MapsActivity::class.java)
                                    startActivity(intent)

                                }
                            })



            }

        }

    }


}