package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pinple_aos.R
import com.naver.maps.map.NaverMapSdk
import com.example.pinple_aos.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 네이버 지도 API 키 설정
        NaverMapSdk.getInstance(requireContext()).client =
            NaverMapSdk.NaverCloudPlatformClient("8w319xk099")

        // 데이터뷰 버튼 클릭 시 이벤트 처리
        binding.dataViewButton.setOnClickListener {
            val mainPinFragment = MainPinFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mainPinFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}