package com.ubaya.anmp_uts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.ubaya.anmp_uts.databinding.ActivityMainBinding
import com.ubaya.anmp_uts.databinding.BeritaListItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // navController = (binding.fragmentContainerView6 as NavHostFragment).navController
        //find by id mencari berdasarkan id seluruh res
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView6)
                as NavHostFragment).navController

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.bottomNav.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout) ||
                super.onSupportNavigateUp()
    }
}