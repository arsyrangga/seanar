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
    val average_annual_revenue: Int,
    val average_catch_weight: Int,
    val years_in_business: Int,
    val number_of_employees: Int,
    val number_of_ships: Int
) : Parcelable