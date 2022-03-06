package com.edgetechs.finstacks

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.edgetechs.finstacks.R
import com.edgetechs.finstacks.screens.home.HomeActivity
import com.edgetechs.finstacks.screens.login.LoginActivity

class SplashScreen : AppCompatActivity() {
    var value : Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val sharedPreferences=getSharedPreferences("theKey", Context.MODE_PRIVATE)
        value=sharedPreferences.getBoolean("status",false)

        if( value){
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@SplashScreen, HomeActivity::class.java)
                startActivity(intent)
            }, 4000)

        }
        else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent =
                    Intent(this@SplashScreen, LoginActivity::class.java)
                startActivity(intent)
            }, 4000)

        }
    }
}