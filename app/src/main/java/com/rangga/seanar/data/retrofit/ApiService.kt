package com.rangga.seanar.data.retrofit

import com.rangga.seanar.data.parcel.LoginParcel
import com.rangga.seanar.data.parcel.RegisterBorrowerParcel
import com.rangga.seanar.data.parcel.RegisterLenderParcel
import com.rangga.seanar.data.response.LoginResponse
import com.rangga.seanar.data.response.registerborrower.RegisterBorrowerResponse
import com.rangga.seanar.data.response.registerlender.RegisterLenderResponse
import com.rangga.seanar.data.response.totalDonasi.TotalDonasiResponse
import com.rangga.seanar.data.response.totalFunding.TotalFundingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("users/login")
    fun login(
        @Body post: LoginParcel
    ): Call<LoginResponse>

    @POST("lenders/register")
    fun registerLender(
        @Body post: RegisterLenderParcel
    ): Call<RegisterLenderResponse>

    @POST("borrowers/register")
    fun registerBorrower(
        @Body post: RegisterBorrowerParcel
    ): Call<RegisterBorrowerResponse>
    @GET("total-funding/lenders/{userId}")
    fun getTotalFunding(@Path("userId") key: String): Call<TotalFundingResponse>
    @GET("total-donation/lenders/{userId}")
    fun getTotalDonation(@Path("userId") key: String): Call<TotalDonasiResponse>

}