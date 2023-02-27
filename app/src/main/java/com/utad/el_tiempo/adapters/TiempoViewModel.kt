package com.utad.el_tiempo.adapters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.utad.el_tiempo.models.*
import com.utad.modelovistacontrolador.data.remote.ApiRest
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TiempoViewModel: ViewModel(){

    val tiempo = MutableStateFlow(Time())
    val loading = MutableStateFlow(false)


    val coroutineExceptionHandler : CoroutineExceptionHandler =
        (CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            loading.value = false
        })

    fun getTiempo(latitud: Double, longitud: Double) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getTiempo(latitud, longitud)
            if (response.isSuccessful && response.body() != null) {
                tiempo.value = response.body()!!
            } else {
                Log.i("TiempoViewModel" , "getTime:${response.errorBody()?.string() }")
            }
            loading.value = false
        })

    }



}