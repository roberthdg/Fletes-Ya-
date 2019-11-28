package com.example.fletesya

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.util.SparseArray
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.example.fletesya.R
import com.example.fletesya.data.Model.User
import com.example.fletesya.data.Preferences.MyPreferences
import com.example.fletesya.ui.configuracion.SettingsFragment

import com.example.fletesya.ui.ofertas.ofertaFragment
import com.example.fletesya.ui.simulador.SimuladorFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        val preferences = MyPreferences(this)

        if(preferences.getData("REFRESH_TOKEN")==null) {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

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

        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, SimuladorFragment(), "simFragment").commit()


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.simuladorFragment -> {
                val simFrag : Fragment? = supportFragmentManager.findFragmentByTag("simFragment")
                val offFrag : Fragment? = supportFragmentManager.findFragmentByTag("offFragment")

                if(simFrag!=null){
                    if(offFrag==null) {
                        supportFragmentManager.beginTransaction().show(simFrag).commit()
                    } else {
                        supportFragmentManager.beginTransaction().hide(offFrag).commit()
                        supportFragmentManager.beginTransaction().show(simFrag).commit()
                    }
                }
            }

                R.id.futureSimFragment -> {
                val simFrag : Fragment? = supportFragmentManager.findFragmentByTag("simFragment")
                val offFrag : Fragment? = supportFragmentManager.findFragmentByTag("offFragment")

                if(simFrag!=null){
                    if(offFrag==null) {
                        supportFragmentManager.beginTransaction().hide(simFrag).commit()
                        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, ofertaFragment(), "offFragment").commit()
                    } else {
                        supportFragmentManager.beginTransaction().hide(simFrag).commit()
                        supportFragmentManager.beginTransaction().show(offFrag).commit()
                    }
                }

            }

            R.id.settingsFragment -> {
           //     supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, SettingsFragment()).commit()
            }

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

}
