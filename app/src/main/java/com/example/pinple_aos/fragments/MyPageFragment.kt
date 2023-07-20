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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        // Inflate the layout for this fragment
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
    private fun createMyPinList(): ArrayList<MyPin>{ //마이핀
        val myPinList = arrayListOf(
            MyPin(R.drawable.icon_pin_toomany_scared, "서울 서초구 잠원동 122-1", "7시간 전"),
            MyPin(R.drawable.icon_pin_middle_good, "서울 강서구 마곡동", "10시간 전"),
            MyPin(R.drawable.icon_pin_toomany_scared, "서울 마포구 망원동", "12시간 전")
        )
        return myPinList
    }

    private fun createScrapList(): ArrayList<Scrap>{
        val scrapList = arrayListOf(
            Scrap("반포한강공원", "서울 용산구 이촌로72길 62", "보통"),
            Scrap("이촌한강공원", "서울 용산구 이촌로72길 62", "약간 혼잡"),
            Scrap("이촌한강공원", "서울 용산구 이촌로72길 62", "혼잡")
        )
        return scrapList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}