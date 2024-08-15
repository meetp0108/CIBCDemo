package com.vatsal.cibc.service.repository

import com.vatsal.cibc.model.Account

interface AccountRepository {
    suspend fun getAccounts(): List<Account>
}