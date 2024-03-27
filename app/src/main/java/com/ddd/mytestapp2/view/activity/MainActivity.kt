package com.ddd.mytestapp2.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ddd.mytestapp2.R
import com.ddd.mytestapp2.databinding.ActivityMainBinding
import com.ddd.mytestapp2.model.constant.MAIN

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        MAIN = this
        navController = Navigation.findNavController(this, R.id.fragment_container)

        binding?.bottomNavigation?.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.id_menu_menu    -> navController?.navigate(R.id.menuFragment)
                R.id.id_menu_profile -> navController?.navigate(R.id.profileFragment)
                R.id.id_menu_crate   -> navController?.navigate(R.id.crateFragment)
            }
            true
        }

    }

}