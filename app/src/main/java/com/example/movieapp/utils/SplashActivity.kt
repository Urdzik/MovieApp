package com.example.movieapp.utils

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import kotlinx.coroutines.delay


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val iv = findViewById<ImageView>(R.id.iv)
        //Image by mode in system
        when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_NO -> iv.setImageResource(R.drawable.day)
            Configuration.UI_MODE_NIGHT_YES -> iv.setImageResource(R.drawable.night)
            Configuration.UI_MODE_NIGHT_UNDEFINED -> iv.setImageResource(R.drawable.day)
        }

        lifecycleScope.launchWhenCreated {
            delay(1000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}
