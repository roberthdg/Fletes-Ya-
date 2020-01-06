package com.example.fletesya.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fletesya.R
import com.example.fletesya.data.Preferences.MyPreferences

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val preferences = MyPreferences(this)

        if(preferences.getData("REFRESH_TOKEN")==null) {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcheractivity)
    }
}
