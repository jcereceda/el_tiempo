package com.utad.el_tiempo.models

class Time() {
    val current: Current
    val daily: List<Daily>
    val lat: Double
    val lon: Double
    val timezone: String
    val timezone_offset: Int

    init {
        current = Current()
        daily = listOf()
        lat = 0.0
        lon = 0.0
        timezone = ""
        timezone_offset = 0
    }
}