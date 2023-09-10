package com.example.pinple_aos.dto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pinple_aos.R
import com.example.pinple_aos.databinding.MypageScrapListBinding
import com.example.pinple_aos.entity.Scrap

class MyPageScrapAdapter(
    private val scrapList:ArrayList<Scrap>,
    ) : RecyclerView.Adapter<MyPageScrapAdapter.MyViewHolder>(){
    private var dataList: List<Scrap> = emptyList()
    fun setData(dataList: List<Scrap>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    //viewholder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageScrapAdapter.MyViewHolder {
        val view = MypageScrapListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view, dataList)
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
    class MyViewHolder(private val binding: MypageScrapListBinding,
                       private val dataList: List<Scrap>):RecyclerView.ViewHolder(binding.root){
        fun bind(data: Scrap){
            binding.tvScrapPlace.text = data.place //장소
            binding.tvScrapAddress.text = data.address //주소

            //상태에 따라 텍스트, 색상 변경
            //1~4 여유 보통 약간혼잡 매우혼잡
            val state=data.state //상태
            if(state==1) {
                binding.tvState.text="여유"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_veryclear))
                binding.clScrap.setBackgroundResource(R.color.color_bg_veryclear)
                var drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.ellipse_veryclear)
                binding.ivEllipse.setImageDrawable(drawable)
            } else if(state==2) {
                binding.tvState.text="보통"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_middle))
                binding.clScrap.setBackgroundResource(R.color.light_bg_middle)
                var drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.ellipse_middle)
                binding.ivEllipse.setImageDrawable(drawable)
            } else if(state==3) {
                binding.tvState.text="약간 혼잡"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_many))
                binding.clScrap.setBackgroundResource(R.color.light_bg_many)
                var drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.ellipse_many)
                binding.ivEllipse.setImageDrawable(drawable)
            } else if(state==4) {
                binding.tvState.text="매우 혼잡"
                binding.tvState.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_status_toomany))
                binding.clScrap.setBackgroundResource(R.color.light_bg_toomany)
                var drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.ellipse_toomany)
                binding.ivEllipse.setImageDrawable(drawable)
            }

        }
    }
}