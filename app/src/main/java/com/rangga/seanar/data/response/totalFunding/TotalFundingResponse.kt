package com.rangga.seanar.data.response.totalFunding

import com.google.gson.annotations.SerializedName

data class TotalFundingResponse(

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

	@field:SerializedName("total_funding")
	val totalFunding: Int? = null
)
