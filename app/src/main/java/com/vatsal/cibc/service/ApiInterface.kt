package com.vatsal.cibc.service

import com.vatsal.cibc.model.Account
import retrofit2.http.GET

interface ApiInterface {

    @GET("account/{id}")
    suspend fun getAccounts(): List<Account>
}