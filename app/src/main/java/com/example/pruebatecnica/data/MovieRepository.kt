package com.example.pruebatecnica.data

import com.example.pruebatecnica.data.database.dao.MovieDao
import com.example.pruebatecnica.data.database.entities.MovieEntity
import com.example.pruebatecnica.data.database.entities.toDomain
import com.example.pruebatecnica.data.dto.MovieDTOMapper
import com.example.pruebatecnica.data.model.Movie
import com.example.pruebatecnica.data.model.MovieProvider
import com.example.pruebatecnica.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val movieProvider: MovieProvider,
    private val movieDao: MovieDao

) {
    suspend fun getAllMovies(): List<Movie> {
        val response = api.getMovies()
        val movieDTOMapper = MovieDTOMapper()
        val responseMapper = movieDTOMapper.fromMovieDTOListToMovieDomainList(response)
        movieProvider.movies = responseMapper
        return responseMapper;
    }

    suspend fun getAllMoviesFromDatabase(): List<Movie> {
        try {
            val response: List<MovieEntity> =
                movieDao.getAllMoviesFav()
            return response.map { it.toDomain() }
        } catch (e: Exception) {
            throw Exception("Error al descargar.")
        }
    }

    suspend fun insertFavMoviesFromDatabase(movie: Movie, like: Boolean) {
        try {
            val entity = MovieEntity(
                movie.id, movie.posterPath!!, movie.title!!, movie.overview!!,
                movie.voteAverage!!, like
            )
            movieDao.insertMovie(entity)
        } catch (e: Exception) {
            throw Exception("Marcar como favorito")
        }
    }

    suspend fun updateFavMoviesFromDatabase(movie: Movie): Int {
        val entity = MovieEntity(
            movie.id, movie.posterPath!!, movie.title!!, movie.overview!!,
            movie.voteAverage!!, false
        )
        return movieDao.updateMovie(entity)
    }
}