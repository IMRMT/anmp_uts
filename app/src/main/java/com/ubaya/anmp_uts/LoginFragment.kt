package com.ubaya.anmp_uts

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.anmp_uts.databinding.FragmentLoginBinding
import com.ubaya.anmp_uts.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel:UserViewModel
    var account: ArrayList<Account> = ArrayList()
    val IDACCOUNT = ""
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var sharedFile = "com.ubaya.anmp_uts"
        var shared: SharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        val idUser = shared.getInt("ID",1)
        super.onViewCreated(view, savedInstanceState)
//        val userList:ArrayList<User>

        var username = binding.txtUserLogIn.text.toString()
        var password = binding.txtPassLogIn.text.toString()

        var username2 = binding.txtUserLogIn.text
        var password2 = binding.txtPassLogIn.text

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        binding.btnLogIn.setOnClickListener {
//            viewModel.login(username, password)
            Log.d("btnclick", account.toString())
            if (username2.isEmpty() || password2.isEmpty()) {
                Toast.makeText(requireContext(), "$username2 Data cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (account.isNotEmpty()) {
                    val user = account[0]
                    if (user.username == username && user.password == password) {
                        Toast.makeText(requireContext(), "$username Sign In Success", Toast.LENGTH_SHORT).show()
                        val action = LoginFragmentDirections.actionBeritaList(idUser)
                        Navigation.findNavController(it).navigate(action)
                    } else {
                        Toast.makeText(requireContext(), "Username or Password is Incorrect", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "No user data available", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnSignUpLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionSignUp()
            Navigation.findNavController(it).navigate(action)
        }
    }
}