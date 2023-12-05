package com.rangga.seanar.data.response.registerborrower

import com.google.gson.annotations.SerializedName

data class RegisterBorrowerResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("income")
	val income: Int? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("is_lender")
	val isLender: Boolean? = null,

	@field:SerializedName("organization_address")
	val organizationAddress: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("credit_score_category")
	val creditScoreCategory: String? = null,

	@field:SerializedName("organization_name")
	val organizationName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
