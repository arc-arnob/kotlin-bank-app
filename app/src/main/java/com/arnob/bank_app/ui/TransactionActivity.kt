package com.arnob.bank_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnob.bank_app.databinding.ActivityRegisterBinding
import com.arnob.bank_app.databinding.ActivityTransactionBinding
import com.arnob.bank_app.di.ViewModelFactory

class TransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionBinding
    private lateinit var adapter: TransactionAdapter
    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TransactionAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelFactory(applicationContext))[TransactionViewModel::class.java]

        viewModel.transactionList.observe(this) { list ->
            adapter.updateData(list)
        }

        viewModel.loadTransactions() // fetch from DB
    }
}
