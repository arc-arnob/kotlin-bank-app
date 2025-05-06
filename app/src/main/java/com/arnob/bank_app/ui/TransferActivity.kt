package com.arnob.bank_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arnob.bank_app.databinding.ActivityTransferBinding
import com.arnob.bank_app.di.ViewModelFactory
import com.arnob.bank_app.util.PreferenceHelper

class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var viewModel: TransferViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceHelper = PreferenceHelper(this)
        // Initialize View Model
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[TransferViewModel:: class.java]
        // setup observers
        setupObservers()
        // setup click listeners
        setupClickListeners()
    }
    private fun setupObservers(){
        viewModel.transferState.observe(this) {state ->
            when(state){
                is TransferState.Success -> {
                    navigateToDashboard()
                }
                is TransferState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun navigateToDashboard(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
    private fun setupClickListeners(){
        binding.btnSendMoney.setOnClickListener{
            val amountText = binding.etTransferAmount.text.toString()
            val amount = amountText.toDoubleOrNull()
            val receiverUserName = binding.etReceiverUsername.text.toString()
            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Enter a valid amount", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.sendMoney(receiverUserName, amount)
            }
        }
    }
}