package com.rangga.seanar.data.response

import com.google.gson.annotations.SerializedName

data class ListTxResponse(

	@field:SerializedName("data")
	val data: List<ListTx?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ListTx(

	@field:SerializedName("nominal")
	val nominal: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
