package com.rangga.seanar.data.parcel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterBorrowerParcel(
    val email: String,
    val nik: String,
    val phone_number: String,
    val role: String,
    val income: Int,
    val organization_name: String,
    val organization_address: String,
    val credit_score_category: String,
    val password: String,
) : Parcelable