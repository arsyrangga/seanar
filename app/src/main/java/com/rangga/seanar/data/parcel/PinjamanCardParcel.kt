package com.rangga.seanar.data.parcel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PinjamanCardParcel(
    val imageLink: String,
    val title: String,
    val isVerified: Boolean,
    val minPinjaman: Int,
    val returns: String,
    val tenor: String,
    val target: Int,
    val currentPinjaman : Int
) : Parcelable