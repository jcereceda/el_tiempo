package com.utad.el_tiempo.adapters

import android.Manifest
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.el_tiempo.R
import com.utad.el_tiempo.databinding.FragmentPrincipalBinding
import com.utad.el_tiempo.models.Weather
import com.utad.modelovistacontrolador.data.remote.ApiRest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.utad.el_tiempo.models.Ciudad
import com.utad.el_tiempo.models.CiudadResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class PrincipalFragment : Fragment() {

    private var _binding: FragmentPrincipalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    private val viewModel: TiempoViewModel by activityViewModels()

    private lateinit var rvSemana: RecyclerView
    private lateinit var localidad: TextView
    private lateinit var temperatura: TextView
    private lateinit var humedad: TextView
    private var adapter: SemanaAdapter? = null




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val latitud = arguments?.getDouble("latitud")
        val longitud = arguments?.getDouble("longitud")



        rvSemana = binding.rvSemana
        localidad = binding.tvLocalidad
        temperatura = binding.tvTemperatura
        humedad = binding.tvhumedad

        initList()
        listenEvents()

        if (latitud != null && longitud != null) {
            viewModel.getTiempo(latitud, longitud)
        }


    }



    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                launch {
                    viewModel.tiempo.collect {
                        adapter?.data = it.daily
                        adapter?.notifyDataSetChanged()

                        temperatura.text = it.current.temp.toInt().toString() + "º"
                        humedad.text = it.current.humidity.toString() + "%"

                        val milat = it.lat
                        val miLong = it.lon
                        localidad.text = it.timezone
                        //val ciudad = ciudades.ciudades.filter { it.coord.lat ==  milat && it.coord.lon == miLong}.single()
                       // localidad.text = ciudad.name

                        var img = ApiRest.URL_IMG + (it.current.weather?.get(0)?.icon ?: "") + "@2x.png"
                        Picasso.get().load(img).into(binding.iconTiempo)
                        simpleDateFormat.timeZone = TimeZone.getTimeZone("CET")

                        binding.tvFecha.text = getDateString(it.current.dt)
                    }
                }




            }
        }
    }

    /**
     * Funciones para dar formato a la fecha
     */
    private val simpleDateFormat = SimpleDateFormat("dd MMMM, hh:mm", Locale("es", "ES"))
    private fun getDateString(time: Long): String = simpleDateFormat.format(time * 1000L)
    private fun getDateString(time: Int): String = simpleDateFormat.format(time * 1000L)


    private fun initList() {
        val mLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        rvSemana.layoutManager = mLayoutManager
        adapter = SemanaAdapter()
        rvSemana.adapter = adapter

    }
}