package com.arnob.bank_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arnob.bank_app.databinding.ActivityDashboardBinding
import com.arnob.bank_app.di.ViewModelFactory
import com.arnob.bank_app.util.PreferenceHelper

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)
        // Initialize View Model
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[DashboardViewModel:: class.java]
        viewModel.loadBalance()
        // setup observers
        setupObservers()
        // setup click listeners
        setupClickListeners()


    }
    private fun setupClickListeners(){
        binding.btnAction.setOnClickListener {
            preferenceHelper.clearSession()
            navigateToLogin()
        }

        binding.sendAddMoney.setOnClickListener {
            navigateToTransferMoney()
        }

        binding.btnAddMoney.setOnClickListener{
            val amountText = binding.etAmount.text.toString()
            val amount = amountText.toDoubleOrNull()

            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Enter a valid amount", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addBalance(amount)
            }
        }
    }
    private fun navigateToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    private fun navigateToTransferMoney(){
        val intent = Intent(this, TransferActivity::class.java)
        startActivity(intent)
    }
    private fun setupObservers() {
        viewModel.balanceState.observe(this) { state ->
            when (state) {
                is BalanceState.Success-> {
                    val updatedAmount = state.amount
                    binding.tvBalance.text = "Balance: $${"%.2f".format(updatedAmount)}"
                }
                is BalanceState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}