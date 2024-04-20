package com.ubaya.anmp_uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.anmp_uts.databinding.ActivitySignUpBinding
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val username = binding.txtUserSignUp.text.toString()
        val password = binding.txtPassSignUp.text.toString()

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421056/register.php"

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response ->
                val obj = JSONObject(response)
                if (obj.getString("Result") == "Success") {
                    // Registration successful, navigate to the login page or perform any other action
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    // Registration failed, display an error message
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Handle errors or exceptions here
                Log.e("RegisterError", error.printStackTrace().toString())
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        q.add(stringRequest)
    }
}