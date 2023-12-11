package com.rangga.seanar.data.response.post

import com.google.gson.annotations.SerializedName

data class PostFundingTransactionResponse(

	@field:SerializedName("data")
	val data: DataTx? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataTx(

	@field:SerializedName("txn_id")
	val txnId: Int? = null,

	@field:SerializedName("txn_date")
	val txnDate: String? = null,

	@field:SerializedName("src_acc_number")
	val srcAccNumber: String? = null,

	@field:SerializedName("lender_id")
	val lenderId: String? = null,

	@field:SerializedName("dst_acc_number")
	val dstAccNumber: String? = null,

	@field:SerializedName("post_id")
	val postId: Int? = null,

	@field:SerializedName("nominal")
	val nominal: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("txn_status")
	val txnStatus: String? = null
)
