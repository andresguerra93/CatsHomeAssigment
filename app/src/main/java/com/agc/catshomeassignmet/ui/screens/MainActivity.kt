package com.agc.catshomeassignmet.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.agc.catshomeassignmet.R

import com.agc.catshomeassignmet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        //Bottom Navigation
        appBarConfiguration = AppBarConfiguration(
            setOf(
/*                R.id.catListFragment,
                R.id.catDetailFragment*/
            ),
            binding.drawerLayout
        )

        //Toolbar
        setSupportActionBar(binding.toolbar)

        //setupActionBarWithNavController(navController, appBarConfiguration)
        //binding.bottomNavigationView.setupWithNavController(navController)
        val bottomNavigationView = binding.bottomNavigationView


        bottomNavigationView.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.action_catDetailFragment_to_catListFragment -> {
                    try {
                        navController.navigate(R.id.action_catDetailFragment_to_catListFragment)
                    }catch (e: Exception){
                        Toast.makeText(this, "You alredy are in Cats", Toast.LENGTH_SHORT).show()
                    }


                    true
                }

                else -> false
            }
        }

        // DrawerLayout
        binding.navView.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dayMode -> {

                disableDarkMode()
                return true
            }
            R.id.nightMode-> {
                enableDarkMode()
                return true
            }
            // Agrega más casos según tus necesidades
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}