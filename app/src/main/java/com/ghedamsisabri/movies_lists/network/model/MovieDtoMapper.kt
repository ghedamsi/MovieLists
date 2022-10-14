package com.ghedamsisabri.movies_lists.network.model

import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.domain.util.DomainMapper

class MovieDtoMapper  : DomainMapper<MovieDto, Movies> {
    override fun mapToDomainModel(model: MovieDto): Movies {
       return(Movies(
           id = model.id,
           adult=model.adult,
           backdrop_path=model.backdrop_path,
           title=model.title,
           original_language=model.original_language,
           original_title=model.original_title,
           overview=model.overview,
           genre_ids=model.genre_ids,
           popularity=model.popularity,
           release_date=model.release_date,
           video=model.video,
           vote_average=model.vote_average,
           vote_count=model.vote_count,
           ))
    }

    override fun mapFromDomainModel(domainModel: Movies): MovieDto {
        return(MovieDto(
            id = domainModel.id,
            adult=domainModel.adult,
            backdrop_path=domainModel.backdrop_path,
            title=domainModel.title,
            original_language=domainModel.original_language,
            original_title=domainModel.original_title,
            overview=domainModel.overview,
            genre_ids=domainModel.genre_ids,
            popularity=domainModel.popularity,
            release_date=domainModel.release_date,
            video=domainModel.video,
            vote_average=domainModel.vote_average,
            vote_count=domainModel.vote_count,
        ))
    }

    fun toDomainList(initial: List<MovieDto>): List<Movies>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Movies>): List<MovieDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}