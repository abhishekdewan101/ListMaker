package com.adewan.listmaker.tab.games

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.repositories.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface GameTabState {
    object Loading : GameTabState
    object Empty : GameTabState
    data class Success(val data: List<CoreList>) : GameTabState
}

@HiltViewModel
class GameTabViewModel
@Inject
constructor(private val gameRepository: GameRepository, @Named("io") io: CoroutineScope) :
    ViewModel() {

    private val _uiState = MutableStateFlow<GameTabState>(GameTabState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        io.launch {
            gameRepository.getAllGameLists().collect {
                if (it.isEmpty()) {
                    _uiState.value = GameTabState.Empty
                } else {
                    _uiState.value = GameTabState.Success(data = it)
                }
            }
        }
    }

    suspend fun getGamesForList(id: UUID): List<GameListEntry> {
        return gameRepository.getGamesForList(id)
    }
}
