package com.example.apppokemon.retrofit

import androidx.viewpager2.widget.ViewPager2.OffscreenPageLimit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.ZoneOffset


interface PokemonApiService {
    @GET("pokemon")
    fun obtenerPokemon(@Query("offset")offset:Int,@Query("limit")limit:Int):Call<PokemonResponse>

}