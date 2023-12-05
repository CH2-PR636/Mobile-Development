package com.example.echopilah.detail.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echopilah.R
import com.example.echopilah.databinding.ActivityDetailOrganicBinding

class DetailOrganic : AppCompatActivity() {

    private lateinit var binding: ActivityDetailOrganicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrganicBinding.inflate(layoutInflater)
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