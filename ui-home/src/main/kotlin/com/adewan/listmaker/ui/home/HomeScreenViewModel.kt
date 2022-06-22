package com.adewan.listmaker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.listmaker.models.ListType
import com.adewan.listmaker.repositories.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val listRepository: ListRepository) :
    ViewModel() {
    init {
        viewModelScope.launch {
            listRepository.getAllLists().onEach {
                println("Lists -> $it")
            }.collect()
        }
        listRepository.addList(UUID.randomUUID(), "First List", ListType.GAME)
    }
}