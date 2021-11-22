package com.example.mydive

import android.app.Activity
import android.app.LauncherActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.gson.GsonBuilder
import com.kakao.auth.StringSet.id
import com.kakao.usermgmt.StringSet.id
import kotlinx.android.synthetic.main.search_first.*
import okhttp3.*
import java.io.IOException
import java.net.URL

class SearchFirst :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_first)


        //툴바
        actionBar?.setDisplayHomeAsUpEnabled(true)

        //스피너
        var selected = 0

        var myplace = arrayOf("See All","Korea","Brazil","Peru","Belgium")
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, myplace)
        placespinner.adapter=adapter

        placespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected = position//선택사항 넣기
            }
        }

        //검색 버튼
        testbutton.setOnClickListener {
            recyclerView.layoutManager = LinearLayoutManager(this)
            if(selected==0)
                fetchJson("http://192.168.0.7/PHP_connection2.php")
            else if(selected==1)
                fetchJson("http://192.168.0.7/PHP_connection2_korea.php")
            else if(selected==2)
                fetchJson("http://192.168.0.7/PHP_connection2_brazil.php")
            else if(selected==3)
                fetchJson("http://192.168.0.7/PHP_connection2_peru.php")
            else if(selected==4)
                fetchJson("http://192.168.0.7/PHP_connection2_belgium.php")
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

    fun fetchJson(myurl:String){
        val url = URL(myurl)
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("hInfo","Failed to execute request!")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                Log.d("hInfo","Success to execute request! : $body")

                val gson = GsonBuilder().create()
                val list = gson.fromJson(body, JsonObj::class.java)

                runOnUiThread {
                    recyclerView.adapter = hInfoAdapter(list)
                }
            }
        })

    }
}