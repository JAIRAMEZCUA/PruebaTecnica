package com.example.pruebatecnica.data.dto

import com.example.pruebatecnica.data.model.Movie

class MovieDTOMapper {

    private fun fromMovieDTOToMovieDomain(movieDTO: MovieDTO): Movie {
        return Movie(
            movieDTO.id,
            movieDTO.posterPath,
            movieDTO.title,
            movieDTO.overview,
            movieDTO.voteAverage
        )
    }

    fun fromMovieDTOListToMovieDomainList(movieDTOList: List<MovieDTO>): List<Movie> {
        return movieDTOList.map { fromMovieDTOToMovieDomain(it) }
    }
}
