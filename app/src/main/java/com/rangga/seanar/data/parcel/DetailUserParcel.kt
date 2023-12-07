package com.rangga.seanar.data.parcel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUserParcel(
    val username : String,
    val email : String,
    val phoneNumber : String,
) : Parcelable