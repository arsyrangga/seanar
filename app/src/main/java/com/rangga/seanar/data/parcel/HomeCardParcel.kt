package com.rangga.seanar.data.parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeCardParcel(
    val imageLink: String,
    val title: String,
    val description: String,
    val isVerified: Boolean,
    val minPinjaman: Int,
    val returns: String,
    val tenor: String,
) : Parcelable