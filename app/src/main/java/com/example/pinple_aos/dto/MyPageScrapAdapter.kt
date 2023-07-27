package com.example.pinple_aos.dto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pinple_aos.databinding.MypageScrapListBinding
import com.example.pinple_aos.entity.Scrap

class MyPageScrapAdapter(private val scrapList:ArrayList<Scrap>) : RecyclerView.Adapter<MyPageScrapAdapter.MyViewHolder>(){
    private var dataList: List<Scrap> = emptyList()
    fun setData(dataList: List<Scrap>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    //viewholder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageScrapAdapter.MyViewHolder {
        val view = MypageScrapListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    class MyViewHolder(private val binding: MypageScrapListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: Scrap){
            binding.tvScrapPlace.text = data.place //장소
            binding.tvScrapAddress.text = data.address //주소
            binding.tvState.text = data.state //상태
        }
    }
}