package com.adewan.listmaker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.listmaker.db.Collection
import com.adewan.listmaker.repositories.AuthenticationRepository
import com.adewan.listmaker.repositories.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface HomeScreenState {
    data class Result(val data: List<Collection>) : HomeScreenState
    object Empty : HomeScreenState
    object Loading : HomeScreenState
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val listRepository: ListRepository,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            listRepository.getAllLists().collect {
                if (it.isEmpty()) {
                    _uiState.value = HomeScreenState.Empty
                } else {
                    _uiState.value = HomeScreenState.Result(data = it)
                }
            }
        }
        viewModelScope.launch {
            authenticationRepository.authenticateUser()
        }
    }
}