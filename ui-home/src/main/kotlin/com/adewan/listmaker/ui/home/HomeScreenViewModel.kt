package com.adewan.listmaker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.listmaker.repositories.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val listRepository: ListRepository) :
    ViewModel() {
    init {
        viewModelScope.launch {
            listRepository.getAllLists().collect {
                println("Lists Count -> ${it.size}")
            }
        }
    }
}