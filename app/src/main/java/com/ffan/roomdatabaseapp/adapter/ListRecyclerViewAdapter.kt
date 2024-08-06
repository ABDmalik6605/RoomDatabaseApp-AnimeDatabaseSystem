package com.ffan.roomdatabaseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.data.local.entities.Anime

class ListRecyclerViewAdapter : RecyclerView.Adapter<ListRecyclerViewAdapter.AnimeViewHolder>() {

    private var animes: List<Anime> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animes[position])
    }

    override fun getItemCount(): Int = animes.size

    fun setAnimes(animes: List<Anime>) {
        this.animes = animes
        notifyDataSetChanged()
    }

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val favCharTextView: TextView = itemView.findViewById(R.id.favCharTextView)
        private val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)
        private val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)

        fun bind(anime: Anime) {
            nameTextView.text = anime.name
            favCharTextView.text = "Favorite Character: ${anime.favChar}"
            genreTextView.text = "Genre: ${anime.genre}"
            ratingTextView.text = "Rating: ${anime.rating}"
        }
    }
}
