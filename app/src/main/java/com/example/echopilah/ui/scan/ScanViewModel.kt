package com.example.echopilah.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.echopilah.data.remote.response.ScanResponse
import com.example.echopilah.data.remote.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ScanViewModel: ViewModel() {

    private val _scannedImage = MutableLiveData<File?>()

    fun setScannedImage(file: File?) {
        _scannedImage.value = file
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun clearDetect() {
        _scannedImage.value = null
    }


    fun scanSampah(apiService: ApiService, label: String, value: Int): MutableLiveData<ScanResponse?> {
        val result = MutableLiveData<ScanResponse?>()

        val file = _scannedImage.value
        if (file != null) {
            val filePart = MultipartBody.Part.createFormData(
                "image",
                file.name,
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )


            apiService.scanSampah(filePart).enqueue(object :
                Callback<ScanResponse> {
                override fun onResponse(call: Call<ScanResponse>, response: Response<ScanResponse>) {
                    if (response.isSuccessful) {
                        val scanResponse = response.body()
                        result.value = scanResponse
                    } else {
                        result.value = null
                    }
                }

                override fun onFailure(call: Call<ScanResponse>, t: Throwable) {
                    result.value = null
                }
            })
        } else {
            result.value = null
        }

        return result
    }
}