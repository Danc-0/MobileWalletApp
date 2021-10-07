package com.danc.mobilewallet.presentation.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponseItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_transaction.view.*

class TransactionsAdapter(private val data : List<LastTransactionsResponseItem>) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        context = parent.context
        return TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_transaction,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TransactionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun setData(lastTransactionsResponseItem: LastTransactionsResponseItem){
            itemView.tvID.text = lastTransactionsResponseItem.transactionId
            itemView.tvAmount.text = lastTransactionsResponseItem.amount.toString()
        }

    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.setData(data[position])
    }

}