package com.adewan.listmaker.tab.movies

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.MovieListEntry
import com.adewan.listmaker.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface MovieTabState {
    object Loading : MovieTabState
    object Empty : MovieTabState
    data class Success(val data: List<CoreList>) : MovieTabState
}

@HiltViewModel
class MovieTabViewModel
@Inject
constructor(private val movieRepository: MovieRepository, @Named("io") io: CoroutineScope) :
    ViewModel() {
    private val _uiState = MutableStateFlow<MovieTabState>(MovieTabState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        io.launch {
            movieRepository.getAllMovieLists().collect {
                if (it.isEmpty()) {
                    _uiState.value = MovieTabState.Empty
                } else {
                    _uiState.value = MovieTabState.Success(data = it)
                }
            }
        }
    }

    suspend fun getMovieForList(id: UUID): List<MovieListEntry> {
        return movieRepository.getMoviesForList(id = id)
    }
}
