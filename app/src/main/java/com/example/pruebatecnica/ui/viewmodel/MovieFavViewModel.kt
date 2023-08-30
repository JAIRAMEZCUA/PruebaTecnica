package com.example.pruebatecnica.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.data.model.Movie
import com.example.pruebatecnica.domain.GetFavMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavViewModel @Inject constructor(
    private val getMoviesUseCaseFav: GetFavMovieUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    init {
        viewModelScope.launch {
            isLoading.value = true
            _movieList.value = getMoviesUseCaseFav.invoke()
            isLoading.value = false
        }
    }
}