package br.bruno.moviedbsimplelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.bruno.moviedbsimplelist.databinding.ActivityMainBinding
import br.bruno.moviedbsimplelist.models.Movie
import br.bruno.moviedbsimplelist.models.MovieResponse
import br.bruno.moviedbsimplelist.services.MovieApiInterface
import br.bruno.moviedbsimplelist.services.MovieApiService
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvMovieList.layoutManager = LinearLayoutManager(this)
        binding.rvMovieList.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            binding.rvMovieList.adapter = MovieAdapter(movies)
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