package com.ubaya.anmp_uts.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.anmp_uts.Account
import com.ubaya.anmp_uts.Global
import com.ubaya.anmp_uts.LoginActivity
import com.ubaya.anmp_uts.model.Berita
import com.ubaya.anmp_uts.model.User
import org.json.JSONObject

class UserViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<ArrayList<User>>()
    var account: ArrayList<Account> = ArrayList()
    val IDACCOUNT = ""
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
    fun login(username: String, password: String) {
        Log.d("CEKMASUK", "masukvolley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://ubaya.me/native/160421056/login.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val plyObj = data.getJSONObject(0)
                    var users = Account(
                        plyObj.getInt("id"),
                        plyObj.getString(username),
                        plyObj.getString(password)
                    )
                    account.add(users)
                }
                Log.d("cekisiarray", account.toString())
            },
            Response.ErrorListener {
                // Handle error here
                Log.e("apiresult", it.message.toString())
            })
        // Set a tag to identify this request
        stringRequest.tag = TAG
        // Add the request to the queue
        queue?.add(stringRequest)
    }

    fun register (username: String, password: String){
        Log.d("CEKMASUK", "masukvolley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.me/native/160421056/register.php"

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response ->
                val obj = JSONObject(response)
                if (obj.getString("Result") == "Success") {
                    // Registration successful, navigate to the login page or perform any other action
                    Toast.makeText(getApplication(), "Registration successful", Toast.LENGTH_SHORT).show()
                } else {
                    // Registration failed, display an error message
                    Toast.makeText(getApplication(), "Registration failed", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Handle errors or exceptions here
                Log.e("RegisterError", error.printStackTrace().toString())
                Toast.makeText(getApplication(), "Error registering user", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }
    }
}