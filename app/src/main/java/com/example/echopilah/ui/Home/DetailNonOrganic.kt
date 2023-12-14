package com.example.echopilah.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echopilah.databinding.ActivityDetailNonOrganicBinding

class DetailNonOrganic : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNonOrganicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNonOrganicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}