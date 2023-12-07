package com.rangga.seanar.data.response.totalDonasi

import com.google.gson.annotations.SerializedName

data class TotalDonasiBorrowerResponse(

	@field:SerializedName("data")
	val data: donasiBorrowerData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class donasiBorrowerData(

	@field:SerializedName("borrower_id")
	val borrowerId: String? = null,

	@field:SerializedName("donation_balance")
	val donationBalance: String? = null
)
