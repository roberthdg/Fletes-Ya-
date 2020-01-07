package com.example.fletesya.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.example.fletesya.R
import com.example.fletesya.data.Model.User
import com.example.fletesya.data.Preferences.MyPreferences

import com.example.fletesya.ui.ofertas.ofertaFragment
import com.example.fletesya.ui.simulador.SimuladorFragment
import com.example.fletesya.ui.subasta.SubastaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    var currentTag = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        val preferences = MyPreferences(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        setSupportActionBar(findViewById(R.id.toolbar))

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        val navView: NavigationView = findViewById(R.id.side_nav)

        val vista: View = navView.getHeaderView(0)

        val texto: TextView = vista.findViewById(R.id.correo_text) as TextView

        val usuario = Gson().fromJson(preferences.getData("user"), User::class.java)

        texto.text = usuario?.correo

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        side_nav.setupWithNavController(navController)

        bottom_nav.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)

        bottomNavView.setOnNavigationItemSelectedListener(this)

        this.setTitle("Subastas")

        currentTag = "subFragment"

        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, SubastaFragment(), "subFragment").commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.subastaFragment -> {
                this.setTitle("Subastas")
                selectFragment("subFragment")
            }

            R.id.ofertaFragment -> {
                this.setTitle("Ofertas")
                selectFragment("offFragment")
            }

            R.id.simuladorFragment -> {
                this.setTitle("Mapa")
                selectFragment("simFragment")
            }

            //cerrar sesión
            R.id.item3Fragment -> {
                val preferences = MyPreferences(this)
                preferences.clear()
                val intent = Intent(this, loginActivity::class.java)
                startActivity(intent)
            }
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
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
        return NavigationUI.navigateUp(navController, drawer_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    fun selectFragment(tag: String) {

        if(tag!=currentTag) {

            val currentFrag : Fragment? = supportFragmentManager.findFragmentByTag(currentTag)

            val newFrag : Fragment? = supportFragmentManager.findFragmentByTag(tag)

            supportFragmentManager.beginTransaction().hide(currentFrag!!).commit()

            currentTag=tag

            if(newFrag!=null) supportFragmentManager.beginTransaction().show(newFrag!!).commit()

            else {
                if(tag=="offFragment") supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, ofertaFragment(), "offFragment").commit()
                else supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, SimuladorFragment(), "simFragment").commit()
            }
        }
    }

}
