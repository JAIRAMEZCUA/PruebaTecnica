package com.example.pruebatecnica.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.data.model.Movie
import com.example.pruebatecnica.domain.GetMoviesUseCase
import kotlinx.coroutines.launch

class MovieMenuViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private var getMoviesUseCase = GetMoviesUseCase()
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    init {
        viewModelScope.launch {
            isLoading.value = true
            _movieList.value = getMoviesUseCase.invoke()
            isLoading.value = false
        }
    }
}