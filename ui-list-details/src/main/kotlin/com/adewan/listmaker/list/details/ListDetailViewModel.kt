package com.adewan.listmaker.list.details

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.repositories.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


sealed interface ListDetailUiState {
    object Loading : ListDetailUiState
    data class ListDetailState(val listName: String) : ListDetailUiState
}

@HiltViewModel
class ListDetailViewModel @Inject constructor(private val listRepository: ListRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<ListDetailUiState>(ListDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getListDetailsForId(id: String) {
        val list = listRepository.getListForId(id = UUID.fromString(id))
        _uiState.value = ListDetailUiState.ListDetailState(listName = list.title!!)
    }
}