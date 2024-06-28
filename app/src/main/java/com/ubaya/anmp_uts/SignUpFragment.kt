package com.ubaya.anmp_uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.anmp_uts.databinding.FragmentSignUpBinding
import com.ubaya.anmp_uts.viewmodel.UserViewModel


class SignUpFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    var account: ArrayList<Account> = ArrayList()
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = binding.txtUserSignUp.text.toString()
        var password = binding.txtPassSignUp.text.toString()

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnSignUp.setOnClickListener {
//            viewModel.register(username, password)
            val action = SignUpFragmentDirections.actionSignUpLogIn()
            Navigation.findNavController(it).navigate(action)
        }
    }
}