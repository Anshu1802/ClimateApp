package com.example.weatherapp

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Math.ceil
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN

        val lat=intent.getStringExtra("lat")
        val long=intent.getStringExtra("long")
        Toast.makeText(this,lat+" "+long,Toast.LENGTH_LONG).show()
        getJsonData(lat,long)



    }



    private fun getJsonData(lat:String?,long:String?)
    {
        val API_KEY="92bd22e47569e9f253b250a86f3e2edd"
        val queue = Volley.newRequestQueue(this)
        val url ="https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${API_KEY}"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                setValues(response)
            },
            Response.ErrorListener { Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show() })


        queue.add(jsonRequest)
    }

    private fun setValues(response:JSONObject){
        city.text=response.getString("name")
        var lat = response.getJSONObject("coord").getString("lat")
        var long=response.getJSONObject("coord").getString("lon")
        coordinates.text="${lat} , ${long}"
        weather.text=response.getJSONArray("weather").getJSONObject(0).getString("main")
        var tempr=response.getJSONObject("main").getString("temp")
        tempr=((((tempr).toFloat()-273.15)).toInt()).toString()
        temp.text="${tempr}°C"


        var mintemp=response.getJSONObject("main").getString("temp_min")
        mintemp=((((mintemp).toFloat()-273.15)).toInt()).toString()
        min_temp.text="Min "+mintemp+"°C"
        var maxtemp=response.getJSONObject("main").getString("temp_max")
        maxtemp=((ceil((maxtemp).toFloat()-273.15)).toInt()).toString()
        max_temp.text="Max "+maxtemp+"°C"

        pressure.text=response.getJSONObject("main").getString("pressure")
//        humiditydegree.text=response.getJSONObject("main").getString("humidity")
        wind.text=response.getJSONObject("wind").getString("speed")
        degree.text="Degree : "+response.getJSONObject("wind").getString("deg")+"°"
        humidity.text=response.getJSONObject("main").getString("humidity")
//        gust.text="Gust : "+response.getJSONObject("wind").getString("gust")
    }
}

