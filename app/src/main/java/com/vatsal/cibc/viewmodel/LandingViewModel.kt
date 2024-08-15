package com.vatsal.cibc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vatsal.cibc.model.Account
import com.vatsal.cibc.service.repository.AccountRepository
import com.vatsal.cibc.service.repository.AccountRespositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutionException

class LandingViewModel(
    private val accountRepository: AccountRepository = AccountRespositoryImpl()
) : ViewModel() {

    private var _uiState = MutableStateFlow<LandingScreenUiState>(LandingScreenUiState.Loading)
    val uiState : StateFlow<LandingScreenUiState> = _uiState

    init {
        getAccounts()
    }

    private fun getAccounts(){
        viewModelScope.launch {
            try {
                val result = accountRepository.getAccounts()
                _uiState.value = LandingScreenUiState.Success(result)

            }catch (e: ExecutionException){
                _uiState.value = LandingScreenUiState.Error
            }
        }
    }
}

sealed class LandingScreenUiState{
    data object Loading : LandingScreenUiState()
    data class Success(val accounts : List<Account>) : LandingScreenUiState()
    data object Error : LandingScreenUiState()
}