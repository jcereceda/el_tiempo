package com.utad.modelovistacontrolador.data.remote

import com.utad.api_peliculas.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    val URL = "https://api.openweathermap.org/data/3.0/"
    val URL_IMG = "https://openweathermap.org/img/wn/"
    val appid = "d729403b82ba67b7cd9c291ded8c295e"
    val lang = "es"

    init {
        initService()
    }

    private fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }
}