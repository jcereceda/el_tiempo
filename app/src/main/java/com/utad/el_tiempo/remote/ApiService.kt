package com.utad.api_peliculas


import com.utad.el_tiempo.models.Time
import com.utad.modelovistacontrolador.data.remote.ApiRest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("onecall")
    suspend fun getTiempo(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = ApiRest.appid,
        @Query("lang") lang: String = ApiRest.lang,
        @Query("cnt") cnt: Int = 8,
        @Query("exclude") exclude: String = "minutely,hourly,alerts",
        @Query("units") units: String = "metric"
    ): Response<Time>

}