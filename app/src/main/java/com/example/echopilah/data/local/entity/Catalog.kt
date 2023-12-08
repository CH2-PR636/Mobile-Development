package com.example.echopilah.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Catalog (
    val image1: Int,
    val image2 : Int,
    val title : String?,
    val description : String?
) : Parcelable