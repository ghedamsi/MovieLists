package com.ghedamsisabri.movies_lists.cache.model

import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.domain.util.DomainMapper

class MoviesEntityMapper: DomainMapper<MoviesEntity, Movies> {
    override fun mapToDomainModel(model: MoviesEntity): Movies {
        return(Movies(
            id = model.id,
            adult=model.adult,
            backdrop_path=model.backdrop_path,
            title=model.title,
            original_language=model.original_language,
            original_title=model.original_title,
            overview=model.overview,
            genre_ids=convertMoviesToList(model.genre_ids),
            popularity=model.popularity,
            release_date=model.release_date,
            video=model.video,
            vote_average=model.vote_average,
            vote_count=model.vote_count,
        ))
    }

    override fun mapFromDomainModel(domainModel: Movies): MoviesEntity {
        return(MoviesEntity(
            id = domainModel.id,
            adult=domainModel.adult,
            backdrop_path=domainModel.backdrop_path,
            title=domainModel.title,
            original_language=domainModel.original_language,
            original_title=domainModel.original_title,
            overview=domainModel.overview,
            genre_ids=convertMoviesListToString(domainModel.genre_ids),
            popularity=domainModel.popularity,
            release_date=domainModel.release_date,
            video=domainModel.video,
            vote_average=domainModel.vote_average,
            vote_count=domainModel.vote_count,
        ))
    }

    private fun convertMoviesListToString(ingredients: List<String>): String {
        val ingredientsString = StringBuilder()
        for(ingredient in ingredients){
            ingredientsString.append("$ingredient,")
        }
        return ingredientsString.toString()
    }

    private fun convertMoviesToList(ingredientsString: String?): List<String>{
        val list: ArrayList<String> = ArrayList()
        ingredientsString?.let {
            for(ingredient in it.split(",")){
                list.add(ingredient)
            }
        }
        return list
    }

    fun fromEntityList(initial: List<MoviesEntity>): List<Movies>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Movies>): List<MoviesEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}