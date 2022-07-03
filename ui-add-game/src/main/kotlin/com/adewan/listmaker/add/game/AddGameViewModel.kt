package com.adewan.listmaker.add.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.listmaker.models.ListMakerGame
import com.adewan.listmaker.repositories.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

sealed interface AddGameUiState {
    object Loading : AddGameUiState
    data class Results(val results: List<ListMakerGame>, val title: String) : AddGameUiState
}

@HiltViewModel
class AddGameViewModel @Inject constructor(private val gameRepository: GameRepository) :
    ViewModel() {
    private val _uiState =
        MutableStateFlow<AddGameUiState>(AddGameUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = AddGameUiState.Results(
                results = gameRepository.getLatestGames(),
                title = "Coming soon"
            )
        }
    }

    fun saveGameIntoList(parenListId: String, game: ListMakerGame) {
        gameRepository.addGameIntoList(
            id = UUID.randomUUID(),
            name = game.name,
            posterUrl = game.coverImage!!.qualifiedUrl,
            rating = game.rating?.toInt() ?: 0,
            parentList = parenListId,
            releaseDate = System.currentTimeMillis() //TODO: Fix this to be coming from game source
        )
    }

    fun searchForGame(game: String) {
        _uiState.value = AddGameUiState.Loading
        viewModelScope.launch {
            _uiState.value = AddGameUiState.Results(
                results = gameRepository.searchForGame(game),
                title = "Search results"
            )
        }
    }
}