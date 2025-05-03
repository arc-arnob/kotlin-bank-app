package com.arnob.bank_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arnob.bank_app.util.PreferenceHelper
import com.arnob.bank_app.databinding.ActivityLoginBinding
import com.arnob.bank_app.di.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)

        // Check if user is already logged in
        if (preferenceHelper.isLoggedIn()) {
            navigateToDashboard()
        }

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[LoginViewModel::class.java]

        // Setup observers
        setupObservers()

        // Setup click listeners
        setupClickListeners()
    }
    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    private fun setupClickListeners() {
        // Login Button Binder
        binding.btnLogin.setOnClickListener {
            val username = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            viewModel.login(username, password)
        }
        // Register Account Binder


        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupObservers() {
        viewModel.loginState.observe(this) { state ->
            when (state) {
                is LoginState.Initial -> {
                    // Do nothing
                }
                is LoginState.Loading -> {
//                    showLoading(true)
                }
                is LoginState.Success -> {
//                    showLoading(false)
                    navigateToDashboard()
                    finish()
                }
                is LoginState.Error -> {
//                    showLoading(false)
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}