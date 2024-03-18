package com.example.apppokemon.retrofit

import retrofit2.Call
import retrofit2.http.GET


interface PokemonApiService {
    @GET("pokemon")
    fun obtenerPokemon():Call<PokemonResponse>

}