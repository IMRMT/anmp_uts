package com.ubaya.anmp_uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.anmp_uts.databinding.ActivitySignUpBinding
import com.ubaya.anmp_uts.model.User
import com.ubaya.anmp_uts.viewmodel.UserViewModel
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: UserViewModel
    var accounts:ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        viewModel.fetchAll()
        // error di sini, karena viewmodel alluserldnya null
//        accounts = viewModel.allUserLD.value!!

        binding.txtLog.setOnClickListener {
            val intent = Intent(it.context, LoginActivity::class.java)
            it.context.startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            // Call the method to perform registration
            registerUser()
        }
    }

    private fun registerUser() {
        var username = binding.txtUserSignUp.text.toString()
        var password = binding.txtPassSignUp.text.toString()
        var repassword = binding.txtRePassword.text.toString()
        var uName = false

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421056/register.php"

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