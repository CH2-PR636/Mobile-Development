package com.example.echopilah.ui.catalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.echopilah.R
import com.example.echopilah.data.local.entity.Catalog
import com.example.echopilah.databinding.ActivityDetailCatalogBinding

class DetailCatalog : AppCompatActivity() {

    private lateinit var binding : ActivityDetailCatalogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataCatalog = intent.getParcelableExtra<Catalog>(KEY_CATALOG)

        if (dataCatalog != null) {
            val img = binding.detailImage
            val judul = binding.title
            val desc = binding.description

            img.setImageResource(dataCatalog.image2)
            judul.text = dataCatalog.title
            desc.text = dataCatalog.description
        }

        setupAction()

    }

    private fun setupAction() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
    companion object {
        const val KEY_CATALOG = "catalog"
    }
}