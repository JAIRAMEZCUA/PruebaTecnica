package com.example.pruebatecnica.di

import android.app.Application
import androidx.room.Room
import com.example.pruebatecnica.BASE_URL
import com.example.pruebatecnica.data.MovieRepository
import com.example.pruebatecnica.data.database.MovieDatabase
import com.example.pruebatecnica.data.network.ApiService
import com.example.pruebatecnica.data.network.MovieService
import com.example.pruebatecnica.domain.GetFavMovieUseCase
import com.example.pruebatecnica.domain.GetMoviesUseCase
import com.example.pruebatecnica.ui.view.MovieDetailActivity
import com.example.pruebatecnica.ui.view.MovieFavActivity
import com.example.pruebatecnica.ui.view.MoviesMenuActivity
import com.example.pruebatecnica.ui.view.adapters.MovieAdapter
import com.example.pruebatecnica.ui.view.adapters.MovieAdapterFav
import com.example.pruebatecnica.ui.viewmodel.MovieFavViewModel
import com.example.pruebatecnica.ui.viewmodel.MovieMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var viewModelModule = module {

    single { MovieService() }
    single { MovieRepository(get(), get()) }

    //UseCase
    factory { GetFavMovieUseCase(get()) }
    factory { GetMoviesUseCase(get()) }

    //viewmoels
    viewModel { MovieFavViewModel(get()) }
    viewModel { MovieMenuViewModel(get()) }


    //Retrofit
    single { provideRetrofit() }
    single { provideApiService(get()) }

    //Activitys
    factory { MovieDetailActivity() }
    factory { MovieFavActivity() }
    factory { MoviesMenuActivity() }
    single { MovieAdapterFav() }
    single(named("MovieAdapter")) { MovieAdapter() }

    //ROOM
    single { provideAppDatabase(get()) }
    single { provideMovieDao(get()) }
}


private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.getMovieDao()

fun provideAppDatabase(application: Application): MovieDatabase {
    return Room.databaseBuilder(application, MovieDatabase::class.java, "app_database")
        .build()
}