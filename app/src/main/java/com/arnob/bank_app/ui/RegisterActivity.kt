package com.arnob.bank_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arnob.bank_app.databinding.ActivityRegisterBinding
import com.arnob.bank_app.di.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[RegisterViewModel::class.java]

        // Setup observers
        setupObservers()

        // Setup click listeners
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener{
            val username = binding.tilUsername.toString().trim()
            val password = binding.tilPassword.toString().trim()
            val name = binding.tilName.toString().trim()

            viewModel.register(username, password, name)
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        println("Registration Success")
    }

    private fun setupObservers() {
        viewModel.registrationState.observe(this) { state ->
            when (state) {

                is RegistrationState.Success -> {
//                    showLoading(false)
                    navigateToLogin()
                    finish()
                }
                is RegistrationState.Error -> {
//                    showLoading(false)
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}