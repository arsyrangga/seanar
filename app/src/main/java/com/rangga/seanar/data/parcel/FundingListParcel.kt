package com.rangga.seanar.data.parcel

import com.google.gson.annotations.SerializedName

data class ListFundingParcel(

    @field:SerializedName("end_date")
    val endDate: Any? = null,

    @field:SerializedName("target_amount")
    val targetAmount: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("organization_name")
    val organizationName: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("duration")
    val duration: Int? = null,

    @field:SerializedName("current_amount")
    val currentAmount: String? = null,

    @field:SerializedName("post_id")
    val postId: Int? = null,

    @field:SerializedName("borrower_id")
    val borrowerId: String? = null,

    @field:SerializedName("link_image")
    val linkImage: String? = null,

    @field:SerializedName("minimum_loan")
    val minimumLoan: String?,

    @field:SerializedName("grade")
    val grade: String? = null,

    @field:SerializedName("is_fraud")
    val isFraud: Boolean? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("return")
    val jsonMemberReturn: String? = null,

    @field:SerializedName("start_date")
    val startDate: String? = null
)