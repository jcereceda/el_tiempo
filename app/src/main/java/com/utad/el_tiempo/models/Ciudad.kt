package com.utad.el_tiempo.models

data class Ciudad(
    val id: Int,
    val name: String,
    val state: String,
    val country: String,
    val coord: Coord
) : java.io.Serializable