package com.example.mydive

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.search_first.*
import okhttp3.*
import java.io.IOException
import java.net.URL

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    var latitude :String?=null
    var longitude :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        //
        latitude = intent.getStringExtra("Lat")
        longitude = intent.getStringExtra("Log")
        if ((latitude != null) and (longitude!=null)) {
            latitude?.let { Log.d("tag", it) }
            longitude?.let { Log.d("tag", it) }
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude!!.toDouble(), longitude!!.toDouble()), 15f))
        }


        //googlemap
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //툴바
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onMapReady(p0: GoogleMap) {

        mMap = p0
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.568256, 126.897240), 10f))
        if ((latitude != null) and (longitude!=null)) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude!!.toDouble(), longitude!!.toDouble()), 15f))
            mMap.addMarker(MarkerOptions().position(LatLng(latitude!!.toDouble(), longitude!!.toDouble()))
                    .title("dive"))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            android.R.id.home-> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}