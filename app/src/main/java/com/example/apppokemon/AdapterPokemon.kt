package com.example.apppokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppokemon.databinding.ActivityMainBinding
import com.example.apppokemon.databinding.ItemPokemonBinding
import com.example.apppokemon.retrofit.Pokemon

class AdapterPokemon
    :RecyclerView.Adapter<AdapterPokemon.ViewHolder>() {

    private var lista= ArrayList<Pokemon>()
    inner class ViewHolder (val binding: ItemPokemonBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount()= lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(lista[position]){
                binding.tvnompokemon.text = name
                val arrayUrl = url.split("/")
                Glide.with(itemView.context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${arrayUrl[arrayUrl.size -2]}.png")
                    .into(binding.ivpokemon)
            }
        }
    }
    fun agregarPokemon(nuevospokemon:List<Pokemon>){
        lista.addAll(nuevospokemon)
        notifyDataSetChanged()
    }

}