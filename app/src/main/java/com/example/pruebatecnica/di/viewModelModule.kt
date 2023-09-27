package com.example.pruebatecnica.di

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.pruebatecnica.BASE_URL
import com.example.pruebatecnica.R
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
import com.example.pruebatecnica.ui.view.auth.AuthViewModel
import com.example.pruebatecnica.ui.view.auth.LoginActivity
import com.example.pruebatecnica.ui.view.auth.LoginFragmentActions
import com.example.pruebatecnica.ui.view.auth.LoginFragmentActionsImpl
import com.example.pruebatecnica.ui.view.auth.SignUpFragmentActions
import com.example.pruebatecnica.ui.view.auth.SignUpFragmentActionsImpl
import com.example.pruebatecnica.ui.viewmodel.MovieFavViewModel
import com.example.pruebatecnica.ui.viewmodel.MovieMenuViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var viewModelModule = module {
    //Activitys
    single { LoginActivity() }
    factory { MovieDetailActivity() }
    factory { MovieFavActivity() }
    factory { MoviesMenuActivity() }
    factory { MovieAdapterFav() }

    factory(named("MovieAdapter")) { MovieAdapter() }

    factory { MovieService() }

    single { provideRetrofit() }
    //Retrofit
    single { provideApiService(get()) }

    //ROOM
    single { provideAppDatabase(get()) }
    single { provideMovieDao(get()) }

    single { MovieRepository(get(), get()) }

    //UseCase
    factory { GetFavMovieUseCase(get()) }
    factory { GetMoviesUseCase(get()) }

    //viewmodels
    viewModel { AuthViewModel() }
    viewModel { MovieFavViewModel(get()) }
    viewModel { MovieMenuViewModel(get()) }

    factory { (context: Context) ->
        val navHostFragment = (context as FragmentActivity).supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    //Fragments
    factory<SignUpFragmentActions> { SignUpFragmentActionsImpl(get()) }
    factory<LoginFragmentActions> { LoginFragmentActionsImpl(get()) }

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

fun provideNavController(activity: FragmentActivity): NavController {
    val fragmentManager = activity.supportFragmentManager
    val navHostFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment)
    return navHostFragment?.findNavController()
        ?: throw IllegalStateException("NavController not found")
}

fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.getMovieDao()

fun provideAppDatabase(application: Application): MovieDatabase {
    return Room.databaseBuilder(application, MovieDatabase::class.java, "app_database")
        .build()
}