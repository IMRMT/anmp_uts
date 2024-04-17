package com.ubaya.anmp_uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.anmp_uts.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var account: ArrayList<Account> = ArrayList()

    val IDACCOUNT = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val t = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421056/login.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val plyObj = data.getJSONObject(0)
                    val users = Account(
                        plyObj.getInt("id"),
                        plyObj.getString("username"),
                        plyObj.getString("password")
                    )
                    account.add(users)
                }
                Log.d("cekisiarray", account.toString())
            },
            Response.ErrorListener {
                // Handle error here
                Log.e("apiresult", it.message.toString())
            }
        )
        t.add(stringRequest)

        binding.btnLogIn.setOnClickListener {
            var username = binding.txtUserLogIn.text.toString()
            var password = binding.txtPassLogIn.text.toString()
            var status = false
            Log.d("btnclick", account.toString())
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "$username Data cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (account.isNotEmpty()) {
                    val user = account[0]
                    if (user.username == username && user.password == password) {
                        Toast.makeText(this, "$username Sign In Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra(IDACCOUNT, user.id)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Username or Password is Incorrect", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "No user data available", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnSignUpLogin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}