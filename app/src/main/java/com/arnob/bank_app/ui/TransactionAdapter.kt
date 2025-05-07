package com.arnob.bank_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnob.bank_app.R
import com.arnob.bank_app.data.model.Transaction
import java.util.*

class TransactionAdapter(private var items: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvType: TextView = view.findViewById(R.id.tvType)
        val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        val tvTimestamp: TextView = view.findViewById(R.id.tvTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = items[position]
        holder.tvType.text = item.type.name
        holder.tvAmount.text = "Amount: $${item.amount}"
        holder.tvTimestamp.text = Date(item.timestamp).toString()
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Transaction>) {
        items = newItems
        notifyDataSetChanged()
    }
}
