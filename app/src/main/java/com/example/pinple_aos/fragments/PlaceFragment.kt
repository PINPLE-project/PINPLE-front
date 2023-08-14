package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pinple_aos.databinding.FragmentPlaceBinding
import com.example.pinple_aos.dto.PlaceAdapter
import com.example.pinple_aos.entity.Place

class PlaceFragment : Fragment() {

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PlaceAdapter //Place 어댑터

    private var selectedButton: View? = null //선택된 버튼을 저장

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        populateRecyclerView()

        //검색 버튼 클릭 이벤트 처리
        binding.btnSearch.setOnClickListener {
            // 버튼 클릭 시 키보드 내리기
            val inputMethodManager = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
            inputMethodManager?.hideSoftInputFromWindow(binding.btnSearch.windowToken, 0)

            // 검색 내용 전달
            val query = binding.placeSearch.text.toString().trim()
            adapter.filter.filter(query)
        }
        
        //검색창 엔터 입력 시 검색 버튼 클릭
        val editText = binding.placeSearch
        val searchBtn = binding.btnSearch
        
        editText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                searchBtn.performClick() //완료 액션 시 검색 버튼 클릭
                //키보드 내리기
                val inputMethodManager = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
                inputMethodManager?.hideSoftInputFromWindow(editText.windowToken, 0)
                true
            } else {
                false
            }
        }

       //정렬 버튼 클릭 이벤트 처리
        binding.btnHigh.isSelected = true //기본값으로 선택됨
        adapter.sortByHighState()

        binding.btnHigh.setOnClickListener{
            resetButton(binding.btnDic, binding.btnHigh, binding.btnLow) //버튼 초기화
            binding.btnHigh.isSelected = true
            adapter.sortByHighState()
        }
        binding.btnLow.setOnClickListener {
            resetButton(binding.btnDic, binding.btnHigh, binding.btnLow) //버튼 초기화
            binding.btnLow.isSelected = true
            adapter.sortByLowState()
        }
        binding.btnDic.setOnClickListener {
            resetButton(binding.btnDic, binding.btnHigh, binding.btnLow) //버튼 초기화
            binding.btnDic.isSelected = true
            adapter.sortByName()
        }


    }

    //버튼 초기화 기능
    private fun resetButton(btn1:Button, btn2:Button, btn3:Button){
        btn1.isSelected=false
        btn2.isSelected=false
        btn3.isSelected=false
    }

    //RecyclerView 어댑터 생성, 연결 등 초기화
    private fun setupRecyclerView() {
        //datalist 받아와서 어댑터로 전달
        adapter = PlaceAdapter(ArrayList(), requireContext())

        //RecyclerView에 어댑터 연결
        binding.rvPlaceList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvPlaceList.adapter = adapter
    }

    //RecyclerView 데이터 채우기
    private fun populateRecyclerView() {
        val placeList = createPlaceList()
        adapter.setData(placeList)

        //state에 따라 color 지정
    }

    //datalist 생성
    private fun createPlaceList(): ArrayList<Place> {
        val placeList = arrayListOf(
            Place("반포한강공원", "서울 서초구 신반포로11길 40", 4),
            Place("이촌한강공원", "서울 용산구 이촌로72길 62", 3),
            Place("이촌한강공원", "서울 용산구 이촌로72길 62", 2),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 1),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 4),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 2),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 3),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 2),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 2),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 1),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 4),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 2),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 3),
            Place("국립중앙박물관·용산가족공원", "서울 용산구 서빙고로 137", 1)
        )
        return placeList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

