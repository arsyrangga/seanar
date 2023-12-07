package com.rangga.seanar.data.retrofit

import com.rangga.seanar.data.parcel.LoginParcel
import com.rangga.seanar.data.parcel.RegisterBorrowerParcel
import com.rangga.seanar.data.parcel.RegisterLenderParcel
import com.rangga.seanar.data.response.LoginResponse
import com.rangga.seanar.data.response.listDonasi.CloseDonationBorrowerResponse
import com.rangga.seanar.data.response.listDonasi.ListDonasiResponse
import com.rangga.seanar.data.response.listDonasi.OpenDonationBorrowerResponse
import com.rangga.seanar.data.response.listFunding.CloseFundingBorrowerResponse
import com.rangga.seanar.data.response.listFunding.ListFundingResponse
import com.rangga.seanar.data.response.listFunding.OpenFundingBorrowerResponse
import com.rangga.seanar.data.response.registerborrower.RegisterBorrowerResponse
import com.rangga.seanar.data.response.registerlender.RegisterLenderResponse
import com.rangga.seanar.data.response.totalDonasi.TotalDonasiBorrowerResponse
import com.rangga.seanar.data.response.totalDonasi.TotalDonasiResponse
import com.rangga.seanar.data.response.totalFunding.TotalFundingBorrowerResponse
import com.rangga.seanar.data.response.totalFunding.TotalFundingResponse
import com.rangga.seanar.data.response.user.UserDetailBorrowerResponse
import com.rangga.seanar.data.response.user.UserDetailResponse
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

    @GET("lenders/{userId}")
    fun getUserDetail(@Path("userId") key: String): Call<UserDetailResponse>

    @GET("borrowers/{userId}")
    fun getUserDetailBorrower(@Path("userId") key: String): Call<UserDetailBorrowerResponse>


    @GET("funding-posts")
    fun getListFunding(): Call<ListFundingResponse>

    @GET("donation-posts")
    fun getListDonation(): Call<ListDonasiResponse>

    @GET("funding-balance/borrowers/{userId}")
    fun getTotalFundingBalanceBorrower(@Path("userId") key: String): Call<TotalFundingBorrowerResponse>

    @GET("donation-balance/borrowers/{userId}")
    fun getTotalDonationBalanceBorrower(@Path("userId") key: String): Call<TotalDonasiBorrowerResponse>

    @GET("open/funding-posts/borrowers/{id}")
    fun getOpenFundingBorrower(@Path("id") key: String): Call<OpenFundingBorrowerResponse>

    @GET("close/funding-posts/borrowers/{id}")
    fun getCloseFundingBorrower(@Path("id") key: String): Call<CloseFundingBorrowerResponse>

    @GET("open/donation-posts/borrowers/{id}")
    fun getOpenDonationBorrower(@Path("id") key: String): Call<OpenDonationBorrowerResponse>

    @GET("close/donation-posts/borrowers/{id}")
    fun getCloseDonationBorrower(@Path("id") key: String): Call<CloseDonationBorrowerResponse>

}