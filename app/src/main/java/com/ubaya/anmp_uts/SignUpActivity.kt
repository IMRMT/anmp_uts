package com.ubaya.anmp_uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.anmp_uts.databinding.ActivitySignUpBinding
import com.ubaya.anmp_uts.model.User
import com.ubaya.anmp_uts.viewmodel.UserViewModel
import org.json.JSONObject

class SignUpActivity : AppCompatActivity(), ButtonSignUpListener {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: UserViewModel
    var accounts:ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        viewModel.fetchAll()

        binding.signUpListener = this
    }

    override fun onButtonSignUpClick(v: View) {
        var username = binding.txtUserSignUp.text.toString()
        var password = binding.txtPassSignUp.text.toString()
        var repassword = binding.txtRePassword.text.toString()
        var uName = false

        for (account in accounts) {
            if (account.username == username) {
                uName = true
                break
            }
        }
        if (uName){
            Toast.makeText(this,"Username has been used", Toast.LENGTH_SHORT).show()
        }else{
            if(!username.isEmpty()&&!password.isEmpty()&&!repassword.isEmpty()){
                if(password==repassword){
                    viewModel.newUser(User(username, password))

                }else{
                    Toast.makeText(this, "Password and Repassword must be the same ", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Please insert all data", Toast.LENGTH_SHORT).show()
            }
        }

        Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}