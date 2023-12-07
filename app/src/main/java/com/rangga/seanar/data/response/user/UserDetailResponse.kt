package com.rangga.seanar.data.response.user

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

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

	@field:SerializedName("is_lender")
	val isLender: Boolean? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
