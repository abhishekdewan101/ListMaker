package com.adewan.listmaker.ui.movie.list.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed interface MovieListDetailState {
    object Loading : MovieListDetailState
    object Empty : MovieListDetailState
}

@HiltViewModel class MovieListDetailViewModel @Inject constructor() : ViewModel() {}
