package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pinple_aos.R
import com.example.pinple_aos.databinding.FragmentMyPageBinding
import com.example.pinple_aos.dto.MyPageMyPinAdapter
import com.example.pinple_aos.dto.MyPageScrapAdapter
import com.example.pinple_aos.entity.MyPin
import com.example.pinple_aos.entity.Scrap
import java.text.FieldPosition

class MyPageFragment : Fragment() {

    private var _binding:FragmentMyPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MyPageMyPinAdapter //마이핀 어댑터
    private lateinit var sadapter:MyPageScrapAdapter //스크랩 어댑터


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        populateRecyclerView()


    }


    private fun setupRecyclerView(){
        //mypin
        val myPinList: List<MyPin> = createMyPinList()
        adapter = MyPageMyPinAdapter(ArrayList())
        binding.rvMpMypin.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMpMypin.adapter = adapter
        binding.tvMpMypinCnt.text = myPinList.size.toString()

        //scrap
        val scrapList: List<Scrap> = createScrapList()
        sadapter = MyPageScrapAdapter(ArrayList())
        binding.rvMpScrap.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvMpScrap.adapter = sadapter

        //state에 따라 ellipse, background 선택
    }

    private fun populateRecyclerView(){
        val dataList = createMyPinList()
        adapter.setData(dataList)

        val scrapList = createScrapList()
        sadapter.setData(scrapList)
    }

    //datalist 생성
    private fun createMyPinList(): MutableList<MyPin>{ //마이핀
        var myPinList = mutableListOf(
            MyPin(R.drawable.icon_pin_toomany_scared, "서울 서초구 잠원동 122-1", "7시간 전"),
            MyPin(R.drawable.icon_pin_middle_good, "서울 강서구 마곡동", "10시간 전"),
            MyPin(R.drawable.icon_pin_toomany_scared, "서울 마포구 망원동", "12시간 전")
        )
        return myPinList
    }



    private fun createScrapList(): ArrayList<Scrap>{
        val scrapList = arrayListOf(
            Scrap("반포한강공원", "서울 용산구 이촌로72길 62", 2),
            Scrap("이촌한강공원", "서울 용산구 이촌로72길 62", 3),
            Scrap("이촌한강공원", "서울 용산구 이촌로72길 62", 4)
        )
        return scrapList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}