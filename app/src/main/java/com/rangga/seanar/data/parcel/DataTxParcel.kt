package com.rangga.seanar.data.parcel

import com.google.gson.annotations.SerializedName

data class DataTxParcel(

	@field:SerializedName("src_acc_number")
	val srcAccNumber: String? = null,

	@field:SerializedName("lender_id")
	val lenderId: String? = null,

	@field:SerializedName("dst_acc_number")
	val dstAccNumber: String? = null,

	@field:SerializedName("post_id")
	val postId: Int? = null,

	@field:SerializedName("nominal")
	val nominal: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("txn_status")
	val txnStatus: String? = null
)
