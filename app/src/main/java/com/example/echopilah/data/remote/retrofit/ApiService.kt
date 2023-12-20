package com.example.echopilah.data.remote.retrofit

import com.example.echopilah.data.remote.response.ScanResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("/predict")
    fun scanSampah (
        @Part file: MultipartBody.Part,
    ): Call<ScanResponse>
}