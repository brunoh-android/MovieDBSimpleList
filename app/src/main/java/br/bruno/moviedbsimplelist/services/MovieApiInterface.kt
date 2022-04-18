package br.bruno.moviedbsimplelist.services

import br.bruno.moviedbsimplelist.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=a1cecfbc98de744b54f4e97192be274f")
    fun getMovieList(): Call<MovieResponse>
}