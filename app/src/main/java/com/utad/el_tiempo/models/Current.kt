package com.utad.el_tiempo.models

class Current() {
    val clouds: Int
    val dew_point: Double
    val dt: Int
    val feels_like: Double
    val humidity: Int
    val pressure: Int
    val sunrise: Int
    val sunset: Int
    val temp: Double
    val uvi: Double
    val visibility: Int
    val wind_deg: Int
    val wind_speed: Double
    val weather: List<Weather>? = null

    init {
        clouds = 0
        dew_point = 0.0
        dt = 0
        feels_like = 0.0
        humidity = 0
        pressure = 0
        sunrise = 0
        sunset = 0
        temp = 0.0
        uvi = 0.0
        visibility = 0
        wind_deg = 0
        wind_speed = 0.0
    }
}