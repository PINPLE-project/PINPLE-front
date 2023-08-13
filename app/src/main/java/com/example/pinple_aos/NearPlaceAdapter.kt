package com.example.pinple_aos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class NearPlaceAdapter(private val itemList: ArrayList<NearPlaceItem>) :
    RecyclerView.Adapter<NearPlaceAdapter.ReminderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detailed_place_near_item, parent,false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.buttonStatus.setImageResource(itemList[position].pin)
        holder.textAddress.text = itemList[position].address
        holder.textTime.text = itemList[position].time
        holder.backgroundRecord.setBackgroundResource(itemList[position].backgroundColor)

    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buttonStatus = itemView.findViewById<ImageView>(R.id.iv_mp_pin)
        val textAddress = itemView.findViewById<TextView>(R.id.textAddress)
        val textTime = itemView.findViewById<TextView>(R.id.textTime)
        val backgroundRecord = itemView.findViewById<ConstraintLayout>(R.id.nearPinLayout)
    }

}
