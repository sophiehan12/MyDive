package com.example.mydive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.hinfo.view.*

class hInfoAdapter(var items: JsonObj) : RecyclerView.Adapter<hInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hinfo,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.result.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.result[position]
        holder.setItem(item)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                var intent = Intent(itemView.context, MapActivity::class.java)
                //데이터 전달
                intent.putExtra("Lat",items.result[position].latitude.toString())
                intent.putExtra("Log",items.result[position].longitude.toString())
                //Log.d("tag", items.result[position].longitude.toString())
                itemView.context.startActivity(intent)
            }
        }
        fun setItem(item: hInfo){
            itemView.spotname.text=item.name
            itemView.spotaddress.text=item.address
            itemView.spotdive.text=item.dive

        }

    }
}