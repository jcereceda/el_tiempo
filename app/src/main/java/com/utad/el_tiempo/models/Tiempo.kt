package com.utad.el_tiempo.models

data class Tiempo(
    val current: Current,
    val daily: List<Daily>,
    val lat: Int,
    val lon: Int,
    val timezone: String
): java.io.Serializable {


    data class Current(
        val clouds: Int,
        val dew_point: Double,
        val date: Int,
        val feels_like: Double,
        val humidity: Int,
        val pressure: Int,
        val sunrise: Int,
        val sunset: Int,
        val temp: Double,
        val uvi: Double,
        val visibility: Int,
        val weather: List<Weather>,
        val wind_deg: Int,
        val wind_gust: Int,
        val wind_speed: Double
    ) {
        data class Weather(
            val description: String,
            val icon: String,
            val id: Int,
            val main: String
        )
    }

    data class Daily(
        val clouds: Int,
        val dew_point: Double,
        val date: Int,
        val feels_like: FeelsLike,
        val humidity: Int,
        val moon_phase: Double,
        val moonrise: Int,
        val moonset: Int,
        val pop: Double,
        val pressure: Int,
        val rain: Double,
        val snow: Double,
        val sunrise: Int,
        val sunset: Int,
        val temp: Temp,
        val uvi: Double,
        val weather: List<Weather>,
        val wind_deg: Int,
        val wind_gust: Double,
        val wind_speed: Double
    ) {
        data class FeelsLike(
            val day: Double,
            val eve: Double,
            val morn: Double,
            val night: Double
        )

        data class Temp(
            val day: Double,
            val eve: Double,
            val max: Double,
            val min: Double,
            val morn: Double,
            val night: Double
        )

        data class Weather(
            val description: String,
            val icon: String,
            val id: Int,
            val main: String
        )
    }
}