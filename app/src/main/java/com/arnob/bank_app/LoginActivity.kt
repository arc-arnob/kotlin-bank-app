package com.arnob.bank_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var registerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // Initialize views
        initViews()

        // Set up listeners
        setupListeners()
    }

    private fun initViews() {
        emailInputLayout = findViewById(R.id.tilEmail)
        passwordInputLayout = findViewById(R.id.tilPassword)
        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)
        forgotPasswordTextView = findViewById(R.id.tvForgotPassword)
        registerTextView = findViewById(R.id.tvRegister)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            attemptLogin()
        }

        forgotPasswordTextView.setOnClickListener {
            // Handle forgot password
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
            // You would normally navigate to a password reset screen
        }

        registerTextView.setOnClickListener {
            // Handle registration
            Toast.makeText(this, "Register clicked", Toast.LENGTH_SHORT).show()
            // You would normally navigate to a registration screen
        }
    }
    private fun attemptLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Simple validation
        if (email.isEmpty()) {
            emailInputLayout.error = "Email is required"
            return
        } else {
            emailInputLayout.error = null
        }

        if (password.isEmpty()) {
            passwordInputLayout.error = "Password is required"
            return
        } else {
            passwordInputLayout.error = null
        }

        // Perform login logic here
        // For demonstration purposes
        Toast.makeText(this, "Login attempt with: $email", Toast.LENGTH_SHORT).show()

        // 1. Disable the login button to prevent multiple clicks
        // 2. Show a loading indicator
        // 3. Call your authentication service
        // 4. Handle success/failure responses
    }
}