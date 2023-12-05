package com.rangga.seanar.data.parcel;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize;

@Parcelize
data class LoginParcel(
    val email: String,
    val password: String
) : Parcelable