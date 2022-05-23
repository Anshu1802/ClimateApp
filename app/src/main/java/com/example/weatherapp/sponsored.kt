package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View

class sponsored : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsored)


        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        Handler(Looper.getMainLooper()).postDelayed({
            var intent= Intent(this,splashscreen::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}