package com.rangga.seanar.data.response.listDonasi

import com.google.gson.annotations.SerializedName

data class OpenDonationBorrowerResponse(

	@field:SerializedName("data")
	val data: List<OpenDonation?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class OpenDonation(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("target_amount")
	val targetAmount: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

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

	@field:SerializedName("is_fraud")
	val isFraud: Boolean? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
)
