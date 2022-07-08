package com.adewan.listmaker.ui.movie.list.details

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.database.MovieListEntry
import com.adewan.listmaker.repositories.CoreListRepository
import com.adewan.listmaker.repositories.MovieListEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface MovieListDetailState {
    object Loading : MovieListDetailState
    data class Result(val title: String, val data: List<MovieListEntry>) : MovieListDetailState
}

@HiltViewModel
class MovieListDetailViewModel
@Inject
constructor(
    private val coreListRepository: CoreListRepository,
    private val movieListEntryRepository: MovieListEntryRepository,
    @Named("io") private val io: CoroutineScope
) : ViewModel() {
    private val _uiState = MutableStateFlow<MovieListDetailState>(MovieListDetailState.Loading)
    val uiState = _uiState.asStateFlow()

    suspend fun getListDetailsForId(id: String) {
        io.launch {
            val list = coreListRepository.getListForId(id = UUID.fromString(id))
            assert(list != null) {
                throw IllegalStateException("List for id cannot be null on detail screen")
            }
            movieListEntryRepository.getAllEntriesId(parentListId = list!!.id).collect {
                _uiState.value = MovieListDetailState.Result(title = list.title, data = it)
            }
        }
    }
}
