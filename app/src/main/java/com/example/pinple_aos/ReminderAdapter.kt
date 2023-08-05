package com.example.pinple_aos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class ReminderAdapter(private val itemList: ArrayList<ReminderItem>) :
    RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reminder_list_item, parent,false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.textPlace.text = itemList[position].title
        holder.textTime.text = itemList[position].dateTime
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPlace = itemView.findViewById<TextView>(R.id.textPlace)
        val textTime = itemView.findViewById<TextView>(R.id.textTime)
    }

}
