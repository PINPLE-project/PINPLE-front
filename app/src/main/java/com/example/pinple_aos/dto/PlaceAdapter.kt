package com.example.pinple_aos.dto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pinple_aos.R
import com.example.pinple_aos.databinding.PlaceListBinding
import com.example.pinple_aos.entity.Place

class PlaceAdapter(private val placeList:ArrayList<Place>) : RecyclerView.Adapter<PlaceAdapter.MyViewHolder>(){
    private var dataList: List<Place> = emptyList()
    fun setData(dataList: List<Place>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    //viewholder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceAdapter.MyViewHolder {
        val view = PlaceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    //데이터와 viewholder를 바인딩한다.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    //RecyclerView에서 보여질 item의 총 개수를 반환
    override fun getItemCount(): Int {
        return dataList.size
    }

    //itemview 생성
    class MyViewHolder(private val binding: PlaceListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Place){
            binding.tvPlace.text = data.place //장소
            binding.tvAddress.text = data.address //주소

            //상태에 따라 텍스트, 색상 변경
            //1~4 여유 보통 약간혼잡 매우혼잡
            val state=data.state //상태
            if(state==1) {
                binding.tvState.text="여유"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_veryclear))
                binding.clColor.setBackgroundResource(R.color.color_status_veryclear)
            } else if(state==2) {
                binding.tvState.text="보통"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_middle))
                binding.clColor.setBackgroundResource(R.color.color_status_middle)
            } else if(state==3) {
                binding.tvState.text="약간 혼잡"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_many))
                binding.clColor.setBackgroundResource(R.color.color_status_many)
            } else if(state==4) {
                binding.tvState.text="매우 혼잡"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_toomany))
                binding.clColor.setBackgroundResource(R.color.color_status_toomany)
            }
        }
    }
}