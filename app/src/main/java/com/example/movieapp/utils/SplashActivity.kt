package com.example.movieapp.utils

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.MainActivity
import com.example.movieapp.R

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Image by mode in system
        when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_NO -> iv.setImageResource(R.drawable.day)
            Configuration.UI_MODE_NIGHT_YES -> iv.setImageResource(R.drawable.night)
            Configuration.UI_MODE_NIGHT_UNDEFINED -> iv.setImageResource(R.drawable.day)
        }

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000L)
    }
}
