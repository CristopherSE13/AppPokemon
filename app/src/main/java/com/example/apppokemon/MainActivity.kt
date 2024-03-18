package com.example.apppokemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apppokemon.databinding.ActivityMainBinding
import com.example.apppokemon.retrofit.PokemonApiService
import com.example.apppokemon.retrofit.PokemonResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiRetrofit: Retrofit
    private lateinit var adapterPokemon: AdapterPokemon

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiRetrofit= Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build()
        adapterPokemon = AdapterPokemon()
        binding.rvitempokemon.layoutManager= GridLayoutManager(applicationContext,3)
        binding.rvitempokemon.adapter=adapterPokemon


       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        obtenerPokemonRetrifit()
    }
    private fun obtenerPokemonRetrifit(){
        var ApiService= apiRetrofit.create(PokemonApiService::class.java)
        val pokemonResponse= ApiService.obtenerPokemon()
        pokemonResponse.enqueue(object: Callback<PokemonResponse>{
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                adapterPokemon.agregarPokemon(response.body()!!.results)
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {

            }
        })
    }
}