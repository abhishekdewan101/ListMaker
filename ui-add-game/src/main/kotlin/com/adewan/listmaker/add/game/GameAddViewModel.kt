package com.adewan.listmaker.add.game

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.models.IGDBGame
import com.adewan.listmaker.repositories.GameRepository
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
    data class Result(val data: List<IGDBGame>) : GameAddScreenState
}

@HiltViewModel
class GameAddViewModel
@Inject
constructor(
    private val gameRepository: GameRepository,
    @Named("io") private val io: CoroutineScope
) : ViewModel() {
    private val _uiState = MutableStateFlow<GameAddScreenState>(GameAddScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        io.launch {
            val allSlugs = gameRepository.getAllStoredSlugs()
            val latestGames = gameRepository.getLatestGames()
            val filteredGames = latestGames.filter { allSlugs.contains(it.slug).not() }
            _uiState.value = GameAddScreenState.Result(data = filteredGames)
        }
    }

    fun saveGameIntoList(parenListId: String, game: IGDBGame) {
        io.launch {
            gameRepository.addListEntry(
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
            val allSlugs = gameRepository.getAllStoredSlugs()
            val searchResults = gameRepository.searchForGame(game)
            val filteredGames = searchResults.filter { allSlugs.contains(it.slug).not() }
            _uiState.value = GameAddScreenState.Result(data = filteredGames)
        }
    }
}
