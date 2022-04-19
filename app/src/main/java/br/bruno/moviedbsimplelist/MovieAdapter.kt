package br.bruno.moviedbsimplelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.bruno.moviedbsimplelist.databinding.MovieItenBinding
import br.bruno.moviedbsimplelist.models.Movie
import com.bumptech.glide.Glide


class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    class MovieViewHolder(private var binding: MovieItenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindMovie(movie: Movie) {


            binding.movieTitle.text = movie.title
            binding.movieReleaseDate.text = movie.realease
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(binding.moviePoster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            binding = MovieItenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }

    override fun getItemCount(): Int = movies.size
}