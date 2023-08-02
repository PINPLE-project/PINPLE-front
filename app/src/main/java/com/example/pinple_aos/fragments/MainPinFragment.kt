package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pinple_aos.R
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.overlay.Marker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.MapFragment
import com.naver.maps.map.OnMapReadyCallback
import com.example.pinple_aos.databinding.FragmentMainPinBinding
import com.example.pinple_aos.PinData
import com.naver.maps.map.overlay.OverlayImage

class MainPinFragment : Fragment(R.layout.fragment_main_pin), OnMapReadyCallback {
    private var _binding: FragmentMainPinBinding? = null
    private val binding get() = _binding!!

    private var myNaverMap: NaverMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 네이버 지도 API 키 설정
        NaverMapSdk.getInstance(requireContext()).client =
            NaverMapSdk.NaverCloudPlatformClient("8w319xk099")

        // 네이버 지도 객체 가져오기
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
        mapFragment?.getMapAsync(this)

        // 핀뷰 버튼 클릭 시 이벤트 처리
        binding.pinViewButton.setOnClickListener {
            val mainFragment = MainFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mainFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(naverMap: NaverMap) {
        // 지도 객체가 준비되면 myNaverMap에 할당
        myNaverMap = naverMap

        // 마커 추가
        addMarkers()
    }
    private fun addMarkers() {
        val markerLocations = arrayListOf(
            PinData(37.5125207, 127.0588194, 5, 5),
            PinData(37.5660286, 126.9954924, 5, 5),
            PinData(37.5640027, 126.9847423, 5, 4),
            // LocationData(37.5640027, 126.9847423, 300),
            PinData(37.5149233, 127.1084366, 5, 4),
            PinData(37.5704061, 127.0028419, 5, 3),
            PinData(37.5551703, 126.9234777, 5, 1),

            PinData(37.578077, 126.9728697, 4, 5),
            PinData(37.5724321, 126.976902, 4, 2),
            PinData(37.5827691, 126.991323, 4, 2),

            PinData(37.4824123, 126.8822401, 4, 3),
            PinData(37.5000776, 127.0385419, 4, 4),
            PinData(37.5408155999999, 127.0690315, 4, 5),
            PinData(37.5054982, 127.0011512, 4, 3),
            PinData(37.4910405, 127.004581, 4, 1),
            PinData(37.485363, 126.9010775, 4, 1),
            PinData(37.5523947, 126.9710685, 3,4),
            PinData(37.5041073, 127.0475882, 3, 4),
            PinData(37.5087378, 126.891109, 3,5),
            PinData(37.4799015, 126.9404631, 3, 4),
            PinData(37.5587015, 126.9342139, 3,3),
            PinData(37.5041073, 127.0475882, 3,3),
            PinData(37.6240118, 126.923912, 3,1),
            PinData(37.529557, 126.9641567, 3,2),
            PinData(37.5616893, 127.0370768, 3,3),

            PinData(37.5785279, 126.8915822, 2,4),
            PinData(37.6541639, 127.0566603, 2,4),
            PinData(37.5140077, 126.9424338, 2,5),
            PinData(37.581944, 127.0065117, 2,3),
            PinData(37.5817874, 126.9847201, 2,2),
            PinData(37.5235689999999, 127.0219182, 2,1),
            PinData(37.5434322, 127.0573165, 2,1),
            PinData(37.638904, 127.0249021, 2,3),
            PinData(37.6483816, 127.0344179, 2,1),
            PinData(37.5270348, 127.0390631, 2,4),
            PinData(37.5249782, 126.9252984, 2,5),
            PinData(37.5170751, 126.9033411, 2,3),
            PinData(37.574506, 126.9856172, 2,1),

            PinData(37.5230368, 126.982235, 1,4),
            PinData(37.5539469, 126.9899134, 1,3),
            PinData(37.5288904, 127.0692011, 1,5),
            PinData(37.5525619, 126.8994384, 1,5),
            PinData(37.5097691, 126.9952698, 1,3),
            PinData(37.6267043, 127.0404012, 1,4),
            PinData(37.4359624, 127.0142283, 1,4),
            PinData(37.5446313, 127.0374023, 1,5),
            PinData(37.5657406, 126.8753738, 1,3),
            PinData(37.5172521, 126.9705778, 1,4),
            PinData(37.5164777, 127.0729983, 1,5),
            PinData(37.5185073, 127.0831435, 1,2)
        )

        for (location in markerLocations) {
            // 인구밀도에 따라 다른 마커 아이콘 가져오기
            val markerIcon = when {
                location.populationDensity >= 5 && location.emotion >= 5 -> R.drawable.scared_pin_toomany
                location.populationDensity >= 5 && location.emotion >= 4 -> R.drawable.bad_pin_toomany
                location.populationDensity >= 5 && location.emotion >= 3 -> R.drawable.neutral_pin_toomany
                location.populationDensity >= 5 && location.emotion >= 2 -> R.drawable.good_pin_toomany
                location.populationDensity >= 5 && location.emotion >= 1 -> R.drawable.very_good_pin_toomany

                location.populationDensity >= 4 && location.emotion >= 5 -> R.drawable.scared_pin_many
                location.populationDensity >= 4 && location.emotion >= 4 -> R.drawable.bad_pin_many
                location.populationDensity >= 4 && location.emotion >= 3 -> R.drawable.neutral_pin_many
                location.populationDensity >= 4 && location.emotion >= 2 -> R.drawable.good_pin_many
                location.populationDensity >= 4 && location.emotion >= 1 -> R.drawable.very_good_pin_many

                location.populationDensity >= 3 && location.emotion >= 5 -> R.drawable.scared_pin_middle
                location.populationDensity >= 3 && location.emotion >= 4 -> R.drawable.bad_pin_middle
                location.populationDensity >= 3 && location.emotion >= 3 -> R.drawable.neutral_pin_middle
                location.populationDensity >= 3 && location.emotion >= 2 -> R.drawable.good_pin_middle
                location.populationDensity >= 3 && location.emotion >= 1 -> R.drawable.very_good_pin_middle

                location.populationDensity >= 2 && location.emotion >= 5 -> R.drawable.scared_pin_clear
                location.populationDensity >= 2 && location.emotion >= 4 -> R.drawable.bad_pin_clear
                location.populationDensity >= 2 && location.emotion >= 3 -> R.drawable.neutral_pin_clear
                location.populationDensity >= 2 && location.emotion >= 2 -> R.drawable.good_pin_clear
                location.populationDensity >= 2 && location.emotion >= 1 -> R.drawable.very_good_pin_clear

                location.populationDensity >= 1 && location.emotion >= 5 -> R.drawable.scared_pin_veryclear
                location.populationDensity >= 1 && location.emotion >= 4 -> R.drawable.bad_pin_veryclear
                location.populationDensity >= 1 && location.emotion >= 3 -> R.drawable.neutral_pin_veryclear
                location.populationDensity >= 1 && location.emotion >= 2 -> R.drawable.good_pin_veryclear

                else -> R.drawable.very_good_pin_veryclear
            }

            val overlayImage = OverlayImage.fromResource(markerIcon)

            // 마커 추가
            val marker = Marker()
            marker.position = LatLng(location.latitude, location.longitude)
            marker.icon = overlayImage
            marker.map = myNaverMap

        }
    }
}