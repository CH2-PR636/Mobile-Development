package com.example.echopilah.ui.scan

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.echopilah.R
import com.example.echopilah.data.remote.response.ScanResponse
import com.example.echopilah.data.remote.retrofit.ApiConfig
import com.example.echopilah.databinding.ActivityScanBinding
import com.example.echopilah.ui.rotateBitmap
import java.io.File
import com.example.echopilah.ui.deleteFile

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private val viewModel: ScanViewModel by viewModels()
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }

    private fun setupAction() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnCamera.setOnClickListener { startCamera() }
            btnTrash.setOnClickListener { deletePhoto() }
            btnCek.setOnClickListener { postPhoto() }
        }
    }

    private fun postPhoto() {
        val apiService = ApiConfig.getApiService() // Inisialisasi objek apiService sesuai implementasi Anda
        val label = "" // Ganti dengan label yang sesuai
        val value = 0 // Ganti dengan nilai (value) yang sesuai
        showLoading(true)

        viewModel.scanSampah(apiService, label, value).observe(this) { scanResponse ->
            if (scanResponse != null) {
                showPopup(scanResponse)
                showLoading(false)
                viewModel.clearDetect()
            } else {
                showLoading(false)
                Toast.makeText(this, "Scan Kembali Sampah Kamu Yuk!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showPopup(scanresponse: ScanResponse) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvjudul = dialog.findViewById<TextView>(R.id.tv_judul)
        val tvdetail = dialog.findViewById<TextView>(R.id.tv_detail)
        val buttonDone = dialog.findViewById<Button>(R.id.btn_done)

        tvjudul.text = labelName(label = scanresponse.jsonMemberClass )
        tvdetail.text = labelDesc(label = scanresponse.jsonMemberClass)

        buttonDone.setOnClickListener {
            dialog.dismiss()
        }

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM or Gravity.CENTER
        layoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = layoutParams

        dialog.show()

    }

    private fun labelDesc(label: String): String {
        return when (label) {
            "Organik" -> getString(R.string.organic_desc)
            "Non-Organik" -> getString(R.string.non_organic_desc)
            else -> getString(R.string.nothing)
        }

    }

    private fun labelName(label: String): String {
        return when (label) {
            "Organik" -> getString(R.string.organik)
            "Non-Organik" -> getString(R.string.non_organik)
            else -> getString(R.string.nothing)
        }
    }



    private fun showLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun startCamera() {
        val intent = Intent(this, cameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile

            viewModel.setScannedImage(myFile)
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            Glide.with(this)
                .load(result)
                .centerCrop()
                .into(binding.imageView2)
        }
    }

    private fun deletePhoto() {
        getFile?.let {
            deleteFile(it)
            getFile = null
            viewModel.clearDetect()
            binding.imageView2.setImageDrawable(null) // Bersihkan ImageView
        } ?: run {
            Toast.makeText(this, "Tidak ada foto untuk dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10

    }


}