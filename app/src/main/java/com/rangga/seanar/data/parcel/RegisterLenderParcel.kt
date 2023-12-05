package com.rangga.seanar.data.parcel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterLenderParcel(
    val email: String,
    val phone_number: String,
    val username: String,
    val password: String
) : Parcelable