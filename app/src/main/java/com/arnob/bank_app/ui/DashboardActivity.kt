package com.arnob.bank_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arnob.bank_app.databinding.ActivityDashboardBinding
import com.arnob.bank_app.util.PreferenceHelper

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)
        // setup observers
//        setupObservers() Not needed i think for now.
        // setup click listeners
        setupClickListeners()


    }
    private fun setupClickListeners(){
        binding.btnAction.setOnClickListener {
            preferenceHelper.clearSession()
            navigateToLogin()
        }
    }
    private fun navigateToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}