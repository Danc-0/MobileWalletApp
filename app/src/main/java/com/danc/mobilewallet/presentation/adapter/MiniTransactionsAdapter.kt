package com.danc.mobilewallet.presentation.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danc.mobilewallet.R
import com.danc.mobilewallet.domain.models.Response.LastTransactionsResponseItem
import com.danc.mobilewallet.domain.models.Response.MiniStatementResponseItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_transaction.view.*
import kotlinx.android.synthetic.main.trans_item.view.*

class MiniTransactionsAdapter(private val data : List<MiniStatementResponseItem>) : RecyclerView.Adapter<MiniTransactionsAdapter.TransactionViewHolder>() {

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        context = parent.context
        return TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.trans_item,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TransactionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun setData(lastTransactionsResponseItem: MiniStatementResponseItem){
            itemView.customerCode.text = lastTransactionsResponseItem.customerId
            itemView.transCode.text = lastTransactionsResponseItem.transactionId
            itemView.transType.text = lastTransactionsResponseItem.transactionType
            itemView.loanAmount.text = lastTransactionsResponseItem.amount.toString()
            itemView.balanceAmount.text = lastTransactionsResponseItem.balance.toString()
        }

    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.setData(data[position])
    }

}