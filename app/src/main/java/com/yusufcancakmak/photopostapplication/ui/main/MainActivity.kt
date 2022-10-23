package com.yusufcancakmak.photopostapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yusufcancakmak.photopostapplication.R
import com.yusufcancakmak.photopostapplication.databinding.ActivityMainBinding
import com.yusufcancakmak.photopostapplication.ui.framelays.HomeFragment
import com.yusufcancakmak.photopostapplication.ui.framelays.PofileFragment
import com.yusufcancakmak.photopostapplication.ui.framelays.PostFragment
import com.yusufcancakmak.photopostapplication.ui.framelays.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment= HomeFragment()
        val postFragment= PostFragment()
        val settingsFragment= SettingsFragment()
        val profileFragment= PofileFragment()

        makeCurruntFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_home ->makeCurruntFragment(homeFragment)
                R.id.item_post ->makeCurruntFragment(postFragment)
                R.id.item_profile ->makeCurruntFragment(profileFragment)
                R.id.item_setting ->makeCurruntFragment(settingsFragment)

            }
            true
        }
    }

    private fun makeCurruntFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayt,fragment)
            commit()
        }

    }
