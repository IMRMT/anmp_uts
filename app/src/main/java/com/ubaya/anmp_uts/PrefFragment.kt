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
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.ubaya.anmp_uts.viewmodel.UserViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class PrefFragment : Fragment(), ButtonLogoutListener {

    private lateinit var binding: FragmentPrefBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrefBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val idUser = sharedPreferences.getInt("id_user", 0)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(idUser)

        binding.logoutListener = this

        observeViewModel()

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

    private fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.txtUsername.setText(viewModel.userLD.value?.username)

            binding.user = it
            binding.btnChangePassword?.setOnClickListener {
                var oldPw = binding.txtOldPassword.text.toString()
                var newPw = binding.txtNewPassword.text.toString()
                var renewPw = binding.txtRedoPassword.text.toString()

                if (!oldPw.isEmpty()&&!newPw.isEmpty()&&!renewPw.isEmpty()) {
                    if (oldPw == binding.user!!.password.toString()) {
                        if (newPw == renewPw) {
                            binding.user!!.password = renewPw
                            viewModel.updateUser(binding.user!!)
                            Toast.makeText(activity, "User data successfully changed", Toast.LENGTH_SHORT).show()
                            binding.txtOldPassword.setText("")
                            binding.txtNewPassword.setText("")
                            binding.txtRedoPassword.setText("")
                        } else {
                            Toast.makeText(activity, "New Password doesn't match with the reenter new password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(activity, "Old password doesn't match with the inputted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Please insert all data ", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onButtonLogoutClick(v: View) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences.edit().putBoolean("login", false).apply()
        val logout = sharedPreferences.edit()
        logout.remove("id_user")
        logout.apply()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}