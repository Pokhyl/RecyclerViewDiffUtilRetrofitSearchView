package com.example.recyclerviewdiffutilretrofitsearchview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdiffutilretrofitsearchview.databinding.SingleItemBinding
import com.squareup.picasso.Picasso

class RvAdapter(): RecyclerView.Adapter<RvAdapter.MovieViewHolder>() {

    var movieList = mutableListOf<Movie>()
    var filterList = mutableListOf<Movie>()
    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        this.filterList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = filterList[position]
        holder.binding.category.text = movie.category
        holder.binding.desc.text = movie.desc
        holder.binding.name.text = movie.name
        val imageView: ImageView =  holder.binding.imageView
        Picasso.with(holder.itemView.context)
            .load(movie.imageUrl)
            .into(holder.binding.imageView)

    }

    override fun getItemCount(): Int {
       return filterList.size
    }
     fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val word: String = p0.toString()
                if (word.isEmpty()) {
                    filterList = movieList
                } else{
                    var resultList =  ArrayList<Movie>()
                    for (movie in movieList ) {
                        if (movie.name.toUpperCase().contains(word.toString().toUpperCase())) {
                            resultList.add(movie)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filterList = p1?.values as MutableList<Movie>

                notifyDataSetChanged()//принудительно все обновить
            }

        }
    }
    class MovieViewHolder(var binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}
