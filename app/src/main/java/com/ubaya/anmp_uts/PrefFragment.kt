package com.ubaya.anmp_uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.anmp_uts.databinding.FragmentPrefBinding
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class PrefFragment : Fragment() {

    private lateinit var binding: FragmentPrefBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrefBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.me/native/160421056/get_user.php"

        // Mengambil ID pengguna dari SharedPreferences
        //Ambil userID yang login
        var sharedFile = "com.ubaya.anmp_uts"
        var shared: SharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        val idUser = shared.getInt("ID",0)

        Log.d("FragmentPrefs", "ID Pengguna: $idUser")

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                val arrayData = obj.getJSONArray("data")
                if (arrayData.length() > 0) {
                    val userData = arrayData.getJSONObject(0)

                    binding.txtUsername.text=userData.getString("username")
                }
            },
            {
                Log.e("apiresult", it.message.toString())
            }
        ) {
            // Override metode getParams() untuk menambahkan parameter ID pengguna
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["idUsers"] = idUser.toString()
                return params
            }
        }
        q.add(stringRequest)

        binding.signOutButton.setOnClickListener {
            val intent = Intent(activity, LoginFragment::class.java)
            startActivity(intent)
            // ini untuk tutup aktivitas di main
            activity?.finish()
        }

        binding.btnChangePassword.setOnClickListener {
            try {
                val oldPass = binding.txtOldPassword.text.toString()
                val newPass = binding.txtNewPassword.text.toString()
                val confPass = binding.txtRedoPassword.text.toString()

                // Memeriksa apakah semua field teks diisi
                if (oldPass.isNotEmpty() && newPass.isNotEmpty() && confPass.isNotEmpty()) {

                    // Memeriksa apakah password baru dan konfirmasi password sama
                    if (newPass == confPass) {

                        // Panggil web service untuk mengubah password
                        val idUser = shared.getInt("ID", 0) // Mengambil ID pengguna dari SharedPreferences
                        Log.d("changePass", "ID Pengguna: $idUser")

                        changePassword(idUser, oldPass, newPass)
                    } else {
                        throw Exception("Password baru dan konfirmasi password tidak sesuai")
                    }
                } else {
                    throw Exception("Harap isi semua field teks")
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            Log.d("FragmentPrefs", "Before Dark Mode Change - isChecked: $isChecked")

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.darkModeSwitch.isChecked = isChecked
            } else {
                binding.darkModeSwitch.isChecked = false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            Log.d("FragmentPrefs", "After Dark Mode Change - isChecked: $isChecked")
        }

        binding.notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // In-app notifications are enabled
            } else {
                // In-app notifications are disabled
            }
        }
    }
    private fun changePassword(idUser: Int, oldPassword: String, newPassword: String) {
        val url = "https://ubaya.me/native/160421054/change_pass.php"
        val requestQueue = Volley.newRequestQueue(requireContext())

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    // Password berhasil diubah, Anda dapat menambahkan logika sesuai kebutuhan
                    Toast.makeText(requireContext(), "Password berhasil diubah", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(requireContext(), FragmentPrefs2::class.java)
//                    startActivity(intent)
                } else {
                    // Gagal mengubah password
                    Toast.makeText(requireContext(), "Gagal mengubah password", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // Kesalahan koneksi atau server
                Toast.makeText(requireContext(), "Terjadi kesalahan: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["idUsers"] = idUser.toString()
                params["old_password"] = oldPassword
                params["new_password"] = newPassword
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}