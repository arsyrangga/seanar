package com.rangga.seanar.data.response.totalFunding

import com.google.gson.annotations.SerializedName

data class TotalFundingBorrowerResponse(

	@field:SerializedName("data")
	val data: fundingBorrowerData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class fundingBorrowerData(

	@field:SerializedName("borrower_id")
	val borrowerId: String? = null,

	@field:SerializedName("funding_balance")
	val fundingBalance: String? = null
)
