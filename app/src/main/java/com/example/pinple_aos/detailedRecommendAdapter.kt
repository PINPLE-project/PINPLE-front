package com.example.pinple_aos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class detailedRecommendAdapter(private val itemList: ArrayList<detailedRecommendItem>) :
    RecyclerView.Adapter<detailedRecommendAdapter.ReminderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detailed_place_recommend_item, parent,false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.textPlace.text = itemList[position].place
        holder.textAddress.text = itemList[position].address
        holder.textDensity.text = itemList[position].density
        holder.backgroundRecord.setBackgroundResource(itemList[position].backgroundColor)
        holder.buttonStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, itemList[position].textColor))
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPlace = itemView.findViewById<TextView>(R.id.placeRecText)
        val textAddress = itemView.findViewById<TextView>(R.id.addressRecText)
        val textDensity = itemView.findViewById<TextView>(R.id.densityText)
        val backgroundRecord = itemView.findViewById<View>(R.id.view4)
        val buttonStatus = itemView.findViewById<TextView>(R.id.densityText)
    }

}
