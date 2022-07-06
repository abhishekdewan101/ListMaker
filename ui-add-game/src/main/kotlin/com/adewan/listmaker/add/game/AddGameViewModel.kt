package com.adewan.listmaker.add.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.models.IGDBGame
import com.adewan.listmaker.repositories.GameListEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AddGameUiState {
    object Loading : AddGameUiState
    data class Results(val results: List<IGDBGame>, val title: String) : AddGameUiState
}

@HiltViewModel
class AddGameViewModel
@Inject
constructor(
    private val gameListEntryRepository: GameListEntryRepository,
    @Named("io") private val io: CoroutineScope
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddGameUiState>(AddGameUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value =
                AddGameUiState.Results(
                    results = gameListEntryRepository.getLatestGames(),
                    title = "Coming soon"
                )
        }
    }

    fun saveGameIntoList(parenListId: String, game: IGDBGame) {
        io.launch {
            gameListEntryRepository.addListEntry(
                GameListEntry(
                    slug = game.slug,
                    name = game.name,
                    posterUrl = game.coverImage!!.qualifiedUrl,
                    parentListId = UUID.fromString(parenListId),
                    summary = game.summary ?: ""
                )
            )
        }
    }

    fun searchForGame(game: String) {
        _uiState.value = AddGameUiState.Loading
        viewModelScope.launch {
            _uiState.value =
                AddGameUiState.Results(
                    results = gameListEntryRepository.searchForGame(game),
                    title = "Search results"
                )
        }
    }
}
