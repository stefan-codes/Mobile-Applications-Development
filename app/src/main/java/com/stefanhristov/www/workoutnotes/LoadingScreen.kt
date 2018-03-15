package com.stefanhristov.www.workoutnotes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LoadingScreen : AppCompatActivity() {
    val splashTimeout = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        Handler().postDelayed(object: Runnable{
            public override fun run() {
                val home = Intent (this@LoadingScreen, MainActivity::class.java)
                startActivity(home)
                finish()
            }
        }, splashTimeout.toLong())
    }
}
