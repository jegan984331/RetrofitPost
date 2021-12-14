package com.example.retrofitpost.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.retrofitpost.R
import com.example.retrofitpost.View.Fragement.*
import com.google.android.material.navigation.NavigationView

class Drawer : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)


        val firstFragement = AlaramFragment()
        val secondFragement = BroadcastFragment()
        val threadFragement = ServiceFragment()
        val FourthFragement = PreferrenceFragment()
        val zeroFragement = CppFragment()

        SetCurrentFragement(FourthFragement)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.mipmap.baseline_menu_white_18dp)
        }

        mDrawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.nav_Alaram -> {
                    SetCurrentFragement(firstFragement)

                }
                R.id.nav_Storage -> {
                    SetCurrentFragement(FourthFragement)

                }
                R.id.nav_Service -> {
                    SetCurrentFragement(threadFragement)

                }
                R.id.nav_Broad -> {
                    SetCurrentFragement(secondFragement)

                }
                R.id.nav_Cpp -> {
                    SetCurrentFragement(zeroFragement)

                }
            }

            true
        }
    }

    private fun setSupportActionBar(toolbar: Toolbar) {

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun SetCurrentFragement(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame, fragment)
            commit()
        }
    }
}
