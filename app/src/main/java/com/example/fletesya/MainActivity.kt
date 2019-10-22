package com.example.fletesya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.example.fletesya.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

       private lateinit var navController: NavController

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           setContentView(R.layout.activity_main)
           setSupportActionBar(findViewById(R.id.toolbar))

           val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

           val toggle = ActionBarDrawerToggle(
               this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
           )
           drawerLayout.addDrawerListener(toggle)
           toggle.syncState()

           navController = Navigation.findNavController(this, R.id.nav_host_fragment)

           bottom_nav.setupWithNavController(navController)

           NavigationUI.setupActionBarWithNavController(this, navController)
       }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

       override fun onSupportNavigateUp(): Boolean {
           return NavigationUI.navigateUp(navController, null)
       }

      override fun onOptionsItemSelected(item: MenuItem): Boolean {
           val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
           val navigated = NavigationUI.onNavDestinationSelected(item, navController)
           return navigated || super.onOptionsItemSelected(item)
       }


}

