package com.lucasyago.guest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.lucasyago.guest.R
import com.lucasyago.guest.constants.DataBaseConstants
import com.lucasyago.guest.databinding.ActivityMainBinding
import com.lucasyago.guest.model.StatusGuest
import com.lucasyago.guest.view.adapter.GuestsAdapter
import com.lucasyago.guest.view.listener.OnGuestListener
import com.lucasyago.guest.viewmodel.GuestsViewModel

class MainActivity : AppCompatActivity() {

    private val adapter = GuestsAdapter()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GuestsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(applicationContext)
        //adapter
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(applicationContext, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
            }
        }

        adapter.attachListener(listener)

        observer()
        ///---
        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener {
            startActivity(Intent(applicationContext, GuestFormActivity::class.java))
        }
        setUpNavigation()

    }

    private fun setUpNavigation() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_all_guests, R.id.nav_present, R.id.nav_absent
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_all_guests -> {
                    R.id.nav_all_guests
                    viewModel.getAll()
                }
                R.id.nav_present -> {
                    viewModel.getByStatus(StatusGuest.PRESENCE.status)
                }
                R.id.nav_absent -> {
                    viewModel.getByStatus(StatusGuest.ABSENT.status)
                }

            }
            drawerLayout.close()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    private fun observer() {
        viewModel.guests.observe(this) {
            adapter.updatedGuests(it)
        }
    }
}