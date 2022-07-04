package com.adewan.listmaker.list.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.listmaker.db.GameCollectionEntries
import com.adewan.listmaker.repositories.GameRepository
import com.adewan.listmaker.repositories.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


sealed interface ListDetailUiState {
    object Loading : ListDetailUiState
    data class ListDetailState(val listName: String, val games: List<GameCollectionEntries>) :
        ListDetailUiState
}

@HiltViewModel
class ListDetailViewModel @Inject constructor(
    private val listRepository: ListRepository,
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListDetailUiState>(ListDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getListDetailsForId(id: String) {
        viewModelScope.launch {
            val list = listRepository.getListForId(id = UUID.fromString(id))
            gameRepository.getAllGamesForId(parentListId = id).collect {
                _uiState.value =
                    ListDetailUiState.ListDetailState(listName = list.title!!, games = it)
            }
        }
    }
}