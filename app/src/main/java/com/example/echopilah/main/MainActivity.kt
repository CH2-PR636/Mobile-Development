package com.example.echopilah.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.echopilah.Home.HomeFragment
import com.example.echopilah.R
import com.example.echopilah.databinding.ActivityMainBinding
import com.example.echopilah.catalog.CatalogFragment
import com.example.echopilah.scan.ScanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.background = null
        replaceFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.catalog -> replaceFragment(CatalogFragment())
            }
        true
        }
        binding.apply {
            fabScan.setOnClickListener {
                Intent(this@MainActivity, ScanActivity::class.java).also { intent ->
                    startActivity(intent)
                }
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}