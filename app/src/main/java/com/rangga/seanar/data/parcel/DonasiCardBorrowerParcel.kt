package com.rangga.seanar.data.parcel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DonasiCardBorrowerParcel(
    val imageLink: String,
    val title: String,
    val isVerified: Boolean,
    val terkumpul: Int,
    val target: Int,
    val postId: String
) : Parcelable