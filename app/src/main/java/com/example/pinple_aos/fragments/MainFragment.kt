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
import com.example.pinple_aos.databinding.FragmentMainBinding
import com.example.pinple_aos.LocationData
import com.naver.maps.map.util.MarkerIcons
import com.naver.maps.map.overlay.OverlayImage

class MainFragment : Fragment(R.layout.fragment_main), OnMapReadyCallback {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var myNaverMap: NaverMap? = null

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

        // 네이버 지도 객체 가져오기
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
        mapFragment?.getMapAsync(this)

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

    override fun onMapReady(naverMap: NaverMap) {
        // 지도 객체가 준비되면 myNaverMap에 할당
        myNaverMap = naverMap

        // 마커 추가
        addMarkers()
    }


    private fun addMarkers() {
        val markerLocations = arrayListOf(
            LocationData(37.5125207, 127.0588194, 300),
            LocationData(37.5660286, 126.9954924, 300),
            LocationData(37.5640027, 126.9847423, 300),
            // LocationData(37.5640027, 126.9847423, 300),
            LocationData(37.5149233, 127.1084366, 300),
            LocationData(37.5704061, 127.0028419, 300),
            LocationData(37.5551703, 126.9234777, 300),

            LocationData(37.578077, 126.9728697, 200),
            LocationData(37.5724321, 126.976902, 200),
            LocationData(37.5827691, 126.991323, 200),

            LocationData(37.4824123, 126.8822401, 200),
            LocationData(37.5000776, 127.0385419, 200),
            LocationData(37.5408155999999, 127.0690315, 200),
            LocationData(37.5054982, 127.0011512, 200),
            LocationData(37.4910405, 127.004581, 200),
            LocationData(37.485363, 126.9010775, 200),
            LocationData(37.5523947, 126.9710685, 200),
            LocationData(37.5041073, 127.0475882, 200),
            LocationData(37.5087378, 126.891109, 200),
            LocationData(37.4799015, 126.9404631, 200),
            LocationData(37.5587015, 126.9342139, 200),
            LocationData(37.5041073, 127.0475882, 200),
            LocationData(37.6240118, 126.923912, 200),
            LocationData(37.529557, 126.9641567, 200),
            LocationData(37.5616893, 127.0370768, 200),

            LocationData(37.5785279, 126.8915822, 100),
            LocationData(37.6541639, 127.0566603, 100),
            LocationData(37.5140077, 126.9424338, 100),
            LocationData(37.581944, 127.0065117, 100),
            LocationData(37.5817874, 126.9847201, 100),
            LocationData(37.5235689999999, 127.0219182, 100),
            LocationData(37.5434322, 127.0573165, 100),
            LocationData(37.638904, 127.0249021, 100),
            LocationData(37.6483816, 127.0344179, 100),
            LocationData(37.5270348, 127.0390631, 100),
            LocationData(37.5249782, 126.9252984, 100),
            LocationData(37.5170751, 126.9033411, 100),
            LocationData(37.574506, 126.9856172, 100),

            LocationData(37.5230368, 126.982235, 10),
            LocationData(37.5539469, 126.9899134, 10),
            LocationData(37.5288904, 127.0692011, 10),
            LocationData(37.5525619, 126.8994384, 10),
            LocationData(37.5097691, 126.9952698, 10),
            LocationData(37.6267043, 127.0404012, 10),
            LocationData(37.4359624, 127.0142283, 10),
            LocationData(37.5446313, 127.0374023, 10),
            LocationData(37.5657406, 126.8753738, 10),
            LocationData(37.5172521, 126.9705778, 10),
            LocationData(37.5164777, 127.0729983, 10),
            LocationData(37.5185073, 127.0831435, 10)
        )

        for (location in markerLocations) {
            // 인구밀도에 따라 다른 마커 아이콘 가져오기
            val markerIcon = when {
                location.populationDensity >= 300 -> R.drawable.pin_toomany
                location.populationDensity >= 200 -> R.drawable.pin_many
                location.populationDensity >= 100 -> R.drawable.pin_middle
                else -> R.drawable.pin_veryclear
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