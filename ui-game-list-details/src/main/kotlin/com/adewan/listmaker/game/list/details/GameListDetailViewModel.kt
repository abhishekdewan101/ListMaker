package com.adewan.listmaker.game.list.details

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.repositories.CoreListRepository
import com.adewan.listmaker.repositories.GameListEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface GameListDetailState {
    object Loading : GameListDetailState
    object Empty : GameListDetailState
    data class Result(val title: String, val data: List<GameListEntry>) : GameListDetailState
}

@HiltViewModel
class GameListDetailViewModel
@Inject
constructor(
    private val coreListRepository: CoreListRepository,
    private val gameListEntryRepository: GameListEntryRepository,
    @Named("io") private val io: CoroutineScope
) : ViewModel() {

    private val _uiState = MutableStateFlow<GameListDetailState>(GameListDetailState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getListDetailsForId(id: String) {
        io.launch {
            val list = coreListRepository.getListForId(id = UUID.fromString(id))
            assert(list != null) {
                throw IllegalStateException("List for id cannot be null on detail screen")
            }
            gameListEntryRepository.getAllForId(parentListId = UUID.fromString(id)).collect {
                if (it.isEmpty()) {
                    _uiState.value = GameListDetailState.Empty
                }
                _uiState.value = GameListDetailState.Result(title = list!!.title, data = it)
            }
        }
    }
}
