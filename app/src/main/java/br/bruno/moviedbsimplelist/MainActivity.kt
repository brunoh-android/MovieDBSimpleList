package br.bruno.moviedbsimplelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.bruno.moviedbsimplelist.models.Movie
import br.bruno.moviedbsimplelist.models.MovieResponse
import br.bruno.moviedbsimplelist.services.MovieApiInterface
import br.bruno.moviedbsimplelist.services.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie_list.layoutManager = LinearLayoutManager(this)
        rv_movie_list.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            rv_movie_list.adapter = MovieAdapter(movies)
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {

            override fun onResponse(
                call: retrofit2.Call<MovieResponse>, response: Response<MovieResponse>
            ) {
                return callback(response.body()!!.movies)

            }

            override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {

            }

        })
    }
}