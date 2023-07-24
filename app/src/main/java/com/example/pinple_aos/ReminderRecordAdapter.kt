package com.example.pinple_aos


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

class ReminderRecordAdapter(private val itemList: ArrayList<ReminderRecordItem>) :
    RecyclerView.Adapter<ReminderRecordAdapter.ReminderRecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderRecordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reminder_record_item, parent,false)
        return ReminderRecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderRecordViewHolder, position: Int) {
        holder.textPlaceRecord.text = itemList[position].title
        holder.textTimeRecord.text = itemList[position].dateTime
        holder.buttonStatus.text = itemList[position].status
        holder.buttonStatus.setCompoundDrawablesWithIntrinsicBounds(0, 0, itemList[position].statusIMG, 0)
        holder.backgroundRecord.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, itemList[position].backgroundColor))
        holder.buttonStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, itemList[position].textColor))
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class ReminderRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPlaceRecord = itemView.findViewById<TextView>(R.id.textPlaceRecord)
        val textTimeRecord = itemView.findViewById<TextView>(R.id.textTimeRecord)
        val buttonStatus = itemView.findViewById<Button>(R.id.buttonStatus)
        val backgroundRecord = itemView.findViewById<LinearLayout>(R.id.backgroundRecord)
    }

}
