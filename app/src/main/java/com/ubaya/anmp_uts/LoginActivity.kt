package com.ubaya.anmp_uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings.Global
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.anmp_uts.databinding.ActivityLoginBinding
import com.ubaya.anmp_uts.model.User
import com.ubaya.anmp_uts.viewmodel.UserViewModel
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    var accounts: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val isLogin = sharedPreferences.getBoolean("login", false)
        if(isLogin){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            setContentView(binding.root)
        }

        viewModel.fetchAll()
        // error di sini, karena viewmodel alluserldnya null
//        accounts = viewModel.allUserLD.value

        viewModel.allUserLD.observe(this, Observer { user ->
            user?.let {
                accounts = it as ArrayList<User>
            }
        })
//        val t = Volley.newRequestQueue(this)
//        val url = "https://ubaya.me/native/160421056/login.php"

//        var stringRequest = StringRequest(
//            Request.Method.POST, url,
//            Response.Listener<String> {
//                Log.d("apiresult", it)
//                val obj = JSONObject(it)
//                if (obj.getString("result") == "OK") {
//                    val data = obj.getJSONArray("data")
//                    val plyObj = data.getJSONObject(0)
//                    val users = Account(
//                        plyObj.getInt("id"),
//                        plyObj.getString("username"),
//                        plyObj.getString("password")
//                    )
//                    account.add(users)
//                }
//                Log.d("cekisiarray", account.toString())
//            },
//            Response.ErrorListener {
//                // Handle error here
//                Log.e("apiresult", it.message.toString())
//            }
//        )
//        t.add(stringRequest)

        binding.btnLogIn.setOnClickListener {
            var username = binding.txtUserLogIn.text.toString()
            var password = binding.txtPassLogIn.text.toString()
            var status = false
            if (username.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"Data cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                for (account in accounts) {
                    if (account.username == username && account.password==password) {
                        status = true
                        Toast.makeText(this,"${username} Sign In Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        var idAccount = account.uuid
                        sharedPreferences.edit().putBoolean("login", true).apply()
                        sharedPreferences.edit().putInt("id_user", idAccount).apply()
                        startActivity(intent)
                        finish()
                        break
                    }
                    else{
                        status = false
                    }
                }
                if (!status){
                    Toast.makeText(this,"Username or password is incorrect", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnSignUpLogin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}