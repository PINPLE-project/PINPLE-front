package com.example.pinple_aos.dto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pinple_aos.databinding.MypageMypinListBinding
import com.example.pinple_aos.entity.MyPin

class MyPageMyPinAdapter(private val mypinList:ArrayList<MyPin>) : RecyclerView.Adapter<MyPageMyPinAdapter.MyViewHolder>(){

    private var dataList: List<MyPin> = emptyList()
    fun setData(dataList: List<MyPin>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    //viewholder 생성
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): MyPageMyPinAdapter.MyViewHolder {
        val view = MypageMypinListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    class MyViewHolder(private val binding:MypageMypinListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: MyPin){
            binding.ivMpPin.setImageResource(data.state) //상태
            binding.tvMpMypinAddress.text = data.address //주소
            binding.tvMpMypinTime.text = data.time //경과 시간
        }
    }

}