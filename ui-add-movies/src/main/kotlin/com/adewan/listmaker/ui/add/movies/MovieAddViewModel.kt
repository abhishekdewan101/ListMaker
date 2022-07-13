package com.adewan.listmaker.ui.add.movies

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.database.MovieListEntry
import com.adewan.listmaker.models.TMDBMovie
import com.adewan.listmaker.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface MovieAddState {
    object Loading : MovieAddState
    data class Result(val data: List<TMDBMovie>) : MovieAddState
}

@HiltViewModel
class MovieAddViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
    @Named("io") private val io: CoroutineScope
) : ViewModel() {
    private val _uiState = MutableStateFlow<MovieAddState>(MovieAddState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        io.launch {
            val allIds = movieRepository.getAllStoredIds()
            val trending = movieRepository.getTrending()
            val filteredMovies = trending.filter { allIds.contains(it.id).not() }
            _uiState.value = MovieAddState.Result(data = filteredMovies)
        }
    }

    fun saveMovieIntoList(parentListId: String, movie: TMDBMovie) {
        io.launch {
            movieRepository.insert(
                MovieListEntry(
                    id = movie.id,
                    name = movie.title ?: movie.name ?: "",
                    posterUrl = movie.qualifiedPosterUrl ?: movie.qualifiedBackdropUrl ?: "",
                    parentListId = UUID.fromString(parentListId)
                )
            )
        }
    }

    fun searchForMovie(movie: String) {
        _uiState.value = MovieAddState.Loading
        io.launch {
            val allIds = movieRepository.getAllStoredIds()
            val trending = movieRepository.searchForMoviesAndShows(movie)
            val filteredMovies = trending.filter { allIds.contains(it.id).not() }
            _uiState.value = MovieAddState.Result(data = filteredMovies)
        }
    }
}
