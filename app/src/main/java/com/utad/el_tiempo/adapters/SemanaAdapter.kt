package com.utad.el_tiempo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.el_tiempo.R
import com.utad.el_tiempo.models.Daily
import com.utad.modelovistacontrolador.data.remote.ApiRest
import java.text.SimpleDateFormat
import java.util.*

class SemanaAdapter (var data: List<Daily> = listOf()) : RecyclerView.Adapter<SemanaAdapter.ViewHolder>(){

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val temp = itemView.findViewById<TextView>(R.id.tmpDia)
        val humid = itemView.findViewById<TextView>(R.id.humDia)
        val date = itemView.findViewById<TextView>(R.id.dateDia)
        val icono = itemView.findViewById<ImageView>(R.id.icono)
        fun bind(daily: Daily) {
            temp.text = daily.temp.day.toInt().toString() + "º"
            humid.text = daily.humidity.toString() + "%"
            date.text = getDateString(daily.dt)
            val img = ApiRest.URL_IMG + daily.weather.get(0).icon + "@2x.png"
            Picasso.get().load(img).into(icono)
        }

        private val simpleDateFormat = SimpleDateFormat("dd/MM", Locale("es", "ES"))
        private fun getDateString(time: Long): String = simpleDateFormat.format(time * 1000L)
        private fun getDateString(time: Int): String = simpleDateFormat.format(time * 1000L)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dia_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }

}