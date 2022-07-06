package com.adewan.listmaker.game.list.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.repositories.CoreListRepository
import com.adewan.listmaker.repositories.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ListDetailUiState {
    object Loading : ListDetailUiState
    data class ListDetailState(val listName: String, val games: List<GameListEntry>) :
        ListDetailUiState
}

@HiltViewModel
class GameListDetailViewModel
@Inject
constructor(
    private val coreListRepository: CoreListRepository,
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListDetailUiState>(ListDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getListDetailsForId(id: String) {
        viewModelScope.launch {
            val list = coreListRepository.getListForId(id = UUID.fromString(id))
            gameRepository.getAllGamesForId(parentListId = id).collect {
                _uiState.value =
                    ListDetailUiState.ListDetailState(listName = list?.title ?: "", games = it)
            }
        }
    }
}
