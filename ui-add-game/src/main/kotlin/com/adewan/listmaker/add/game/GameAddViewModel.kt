package com.adewan.listmaker.add.game

import androidx.lifecycle.ViewModel
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

sealed interface GameAddScreenState {
    object Loading : GameAddScreenState
    data class Result(val data: List<IGDBGame>, val title: String) : GameAddScreenState
}

@HiltViewModel
class GameAddViewModel
@Inject
constructor(
    private val gameListEntryRepository: GameListEntryRepository,
    @Named("io") private val io: CoroutineScope
) : ViewModel() {
    private val _uiState = MutableStateFlow<GameAddScreenState>(GameAddScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        io.launch {
            val allSlugs = gameListEntryRepository.getAllStoredSlugs()
            val latestGames = gameListEntryRepository.getLatestGames()
            val filteredGames = latestGames.filter { allSlugs.contains(it.slug).not() }
            _uiState.value = GameAddScreenState.Result(data = filteredGames, title = "Coming soon")
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
        _uiState.value = GameAddScreenState.Loading
        io.launch {
            val allSlugs = gameListEntryRepository.getAllStoredSlugs()
            val searchResults = gameListEntryRepository.searchForGame(game)
            val filteredGames = searchResults.filter { allSlugs.contains(it.slug).not() }
            _uiState.value =
                GameAddScreenState.Result(data = filteredGames, title = "Search results")
        }
    }
}
