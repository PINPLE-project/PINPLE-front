package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageButton
import com.example.pinple_aos.R
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.overlay.Marker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.MapFragment
import com.naver.maps.map.OnMapReadyCallback
import com.example.pinple_aos.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.pinple_aos.LocationData
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.CircleOverlay
import android.graphics.Color
import com.naver.maps.map.CameraUpdate

class MainFragment : Fragment(R.layout.fragment_main), OnMapReadyCallback {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var myNaverMap: NaverMap? = null

    private var circleOverlay: CircleOverlay? = null

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

        // 네이버 지도 객체
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
        myNaverMap = naverMap

        // 마커 추가
        addMarkers()
    }

    private fun zoomInToMarker(marker: Marker) {
        val cameraUpdate = CameraUpdate.zoomTo(15.0)
        myNaverMap?.moveCamera(cameraUpdate)

        val cameraUpdate2 = CameraUpdate.scrollTo(marker.position)
        myNaverMap?.moveCamera(cameraUpdate2)
    }


    private fun addMarkers() {
        val markerLocations = arrayListOf(
            LocationData("강남 MICE 관광특구","서울 강남구 영동대로 513",37.5125207, 127.0588194, 300, "관광특구"),
            LocationData("동대문 관광특구","서울 중구 을지로 281",37.5660286, 126.9954924, 300, "관광특구"),
            LocationData("명동 관광특구","서울특별시 중구 명동길 43",37.5640027, 126.9847423, 300, "관광특구"),

            LocationData("경복궁·서촌마을","서울 종로구 통의동",37.578077, 126.9728697, 200, "고궁,문화유산"),
            LocationData("광화문·덕수궁","서울 종로구 세종로 1-68",37.5724321, 126.976902, 200, "고궁,문화유산"),
            LocationData("창덕궁·종묘","서울 종로구 율곡로 99",37.5827691, 126.991323, 200, "고궁,문화유산"),

            LocationData("가산디지털단지역","서울 금천구 벚꽃로 309",37.4824123, 126.8822401, 200,"인구밀집지역"),
            LocationData("강남역","서울 강남구 역삼동",37.5000776, 127.0385419, 200,"인구밀집지역"),
            LocationData("건대입구역","서울 광진구 아차산로 243",37.5408155999999, 127.0690315, 200,"인구밀집지역"),
            LocationData("고속터미널역","서울 서초구 신반포로 지하 188",37.5054982, 127.0011512, 200,"인구밀집지역"),
            LocationData("교대역","서울 서초구 서초대로 지하 294",37.4910405, 127.004581, 200,"인구밀집지역"),

            LocationData("DMC(디지털미디어시티)","서울 마포구 상암동 1606",37.5785279, 126.8915822, 100,"발달상권"),
            LocationData("창동 신경제 중심지","서울 노원구 노해로 437",37.6541639, 127.0566603, 100,"발달상권"),
            LocationData("노량진","서울 동작구 노량진로 151",37.5140077, 126.9424338, 100,"발달상권"),
            LocationData("낙산공원·이화마을","서울 종로구 낙산길 41",37.581944, 127.0065117, 100,"발달상권"),
            LocationData("북촌한옥마을","서울 종로구 북촌로 53",37.5817874, 126.9847201, 100,"발달상권"),
            LocationData("가로수길","서울 강남구 압구정로 120",37.5235689999999, 127.0219182, 100,"발달상권"),
            LocationData("성수카페거리","서울 성동구 성수동2가 276-5",37.5434322, 127.0573165, 100,"발달상권"),
            LocationData("수유리 먹자골목","서울 강북구 한천로139길 42",37.638904, 127.0249021, 100,"발달상권"),
            LocationData("쌍문동 맛집거리","서울 도봉구 쌍문동 83-6",37.6483816, 127.0344179, 100,"발달상권"),

            LocationData("국립중앙박물관·용산가족공원","서울 용산구 서빙고로 137",37.5230368, 126.982235, 10, "공원"),
            LocationData("남산공원","서울 중구 남산공원길 125-54",37.5539469, 126.9899134, 10, "공원"),
            LocationData("뚝섬한강공원","서울 광진구 자양동 427-6",37.5288904, 127.0692011, 10, "공원"),
            LocationData("망원한강공원","서울 마포구 마포나루길 467",37.5525619, 126.8994384, 10, "공원"),
            LocationData("반포한강공원","서울 서초구 신반포로11길 40",37.5097691, 126.9952698, 10, "공원")
        )

        for (location in markerLocations) {
            // 인구밀도에 따라 다른 마커
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

            // 마커 클릭 리스너 설정
            marker.setOnClickListener {
                showBottomSheetDialog(location)
                true
            }

            marker.setOnClickListener {
                showBottomSheetDialog(location)
                zoomInToMarker(marker)
                true
            }


        }
    }


    private fun showBottomSheetDialog(location: LocationData) {
        // BottomSheetDialog 생성
        val dialogView = layoutInflater.inflate(R.layout.main_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(dialogView)

        // 내용 업데이트
        dialogView.findViewById<TextView>(R.id.textView9)?.text = location.name
        dialogView.findViewById<TextView>(R.id.textView10)?.text = location.address
        dialogView.findViewById<TextView>(R.id.placeCategoryText)?.text = location.placeCategory

        bottomSheetDialog.show()

        bottomSheetDialog.setOnDismissListener {
            circleOverlay?.map = null
        }

        val populationButton = dialogView.findViewById<ImageButton>(R.id.populationButton)
        populationButton.setImageResource(
            when {
                location.populationDensity >= 300 -> R.drawable.info_population_toomany
                location.populationDensity >= 200 -> R.drawable.info_population_many
                location.populationDensity >= 100 -> R.drawable.info_population_middle
                else -> R.drawable.info_population_veryclear
            }
        )

        // 원 그리기
        circleOverlay = CircleOverlay()
        circleOverlay?.center = LatLng(location.latitude, location.longitude)
        circleOverlay?.radius = 200.0 // 반경 200m


        // 혼잡도 따라 원 색상 설정
        circleOverlay?.color = when {
            location.populationDensity >= 300 -> Color.argb(40, 255, 125, 125) // too_many
            location.populationDensity >= 200 -> Color.argb(40, 243, 155, 74) // many
            location.populationDensity >= 100 -> Color.argb(40, 240, 199, 92) // middle
            else -> Color.argb(40, 90, 191, 248) // very_clear
        }

        circleOverlay?.outlineWidth = 0 // 테두리 두께 설정
        circleOverlay?.map = myNaverMap

    }
}