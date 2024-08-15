package com.vatsal.cibc.service.repository

import com.vatsal.cibc.model.Account
import com.vatsal.cibc.service.RetrofitClient

class AccountRespositoryImpl : AccountRepository {

    private val apiClient = RetrofitClient.apiClient

    private val listOfAccounts = listOf(
        Account("CHQ account",100,"1234"),
        Account("SAV account",100,"1234"),
        Account("CREDIT account",100,"1234"),
        Account("MORTGAGE account",100,"1234"),
        Account("RRSP account",100,"1234"),
        Account("GIC account",100,"1234"),
        Account("MUTUAL FUND account",100,"1234"),
        Account("CHQ account",100,"1234"),
    )

    override suspend fun getAccounts(): List<Account> {
       return listOfAccounts
    }
}