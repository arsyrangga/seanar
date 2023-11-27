package com.rangga.seanar.data.parcel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BottomSheetParcel(
    val title : String,
    val nominal : String,
    val desc : String
) : Parcelable