package com.rangga.seanar.data.response.totalDonasi

import com.google.gson.annotations.SerializedName

data class TotalDonasiResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("lender_id")
	val lenderId: String? = null,

	@field:SerializedName("total_donation")
	val totalDonation: Int? = null
)
