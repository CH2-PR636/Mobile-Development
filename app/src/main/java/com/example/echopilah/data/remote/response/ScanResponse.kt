package com.example.echopilah.data.remote.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("Link Image")
	val linkImage: String? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String,
)
