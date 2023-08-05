package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.pinple_aos.R
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.overlay.Marker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.MapFragment
import com.naver.maps.map.OnMapReadyCallback
import com.example.pinple_aos.databinding.FragmentMainPinBinding
import com.example.pinple_aos.PinData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.naver.maps.map.CameraUpdate
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

    private fun zoomInToMarker(marker: Marker) {
        val cameraUpdate = CameraUpdate.zoomTo(15.0)
        myNaverMap?.moveCamera(cameraUpdate)

        val cameraUpdate2 = CameraUpdate.scrollTo(marker.position)
        myNaverMap?.moveCamera(cameraUpdate2)
    }

    private fun addMarkers() {
        val markerLocations = arrayListOf(
            PinData(37.5125207, 127.0588194, "서울 강남구 영동대로 513", 5, 5),
            PinData(37.5660286, 126.9954924, "서울 중구 을지로 281",5, 5),
            PinData(37.5640027, 126.9847423, "서울특별시 중구 명동길 43",5, 4),
            // LocationData(37.5640027, 126.9847423, 300),

            PinData(37.578077, 126.9728697, "서울 종로구 통의동",4, 5),
            PinData(37.5724321, 126.976902, "서울 종로구 세종로 1-68",4, 2),
            PinData(37.5827691, 126.991323, "서울 종로구 율곡로 99",4, 2),

            PinData(37.4824123, 126.8822401, "서울 금천구 벚꽃로 309",4, 3),
            PinData(37.5000776, 127.0385419, "서울 강남구 역삼동",4, 4),
            PinData(37.5408155999999, 127.0690315, "서울 광진구 아차산로 243",4, 5),
            PinData(37.5054982, 127.0011512, "서울 서초구 신반포로 지하 188",4, 3),
            PinData(37.4910405, 127.004581, "서울 서초구 서초대로 지하 294",4, 1),
            PinData(37.485363, 126.9010775, "서울 구로구 도림천로 477",4, 1),
            PinData(37.5523947, 126.9710685, "서울 용산구 한강대로 405", 3,4),


            PinData(37.5785279, 126.8915822, "서울 마포구 상암동 1606",2,4),
            PinData(37.6541639, 127.0566603, "서울 노원구 노해로 437",2,4),
            PinData(37.5140077, 126.9424338, "서울 동작구 노량진로 151",2,5),
            PinData(37.581944, 127.0065117, "서울 종로구 낙산길 41",2,3),
            PinData(37.5817874, 126.9847201, "서울 종로구 북촌로 53",2,2),
            PinData(37.5235689999999, 127.0219182, "서울 강남구 압구정로 120",2,1),
            PinData(37.5434322, 127.0573165, "서울 성동구 성수동2가 276-5",2,1),
            PinData(37.638904, 127.0249021, "서울 강북구 한천로139길 42",2,3),
            PinData(37.6483816, 127.0344179, "서울 도봉구 쌍문동 83-6",2,1),

            PinData(37.5230368, 126.982235, "서울 용산구 서빙고로 137",1,4),
            PinData(37.5539469, 126.9899134, "서울 중구 남산공원길 125-54",1,3),
            PinData(37.5288904, 127.0692011, "서울 광진구 자양동 427-6",1,5),
            PinData(37.5525619, 126.8994384, "서울 마포구 마포나루길 467",1,5),
            PinData(37.5097691, 126.9952698, "서울 서초구 신반포로11길 40",1,3)
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

    private fun showBottomSheetDialog(pin: PinData) {
        // BottomSheetDialog 생성
        val dialogView = layoutInflater.inflate(R.layout.main_pin_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(dialogView)

        // 내용 업데이트
        dialogView.findViewById<TextView>(R.id.titlePlaceText)?.text = pin.address

        bottomSheetDialog.show()


        val populationButton = dialogView.findViewById<ImageButton>(R.id.populationPinButton)
        populationButton.setImageResource(
            when {
                pin.populationDensity >= 5 -> R.drawable.selected_button_toomany
                pin.populationDensity >= 4 -> R.drawable.selected_button_many
                pin.populationDensity >= 3 -> R.drawable.selected_button_middle
                pin.populationDensity >= 2 -> R.drawable.selected_button_clear
                else -> R.drawable.selected_button_veryclear
            }
        )

        val emotionButton = dialogView.findViewById<ImageButton>(R.id.emotionPinButton)
        emotionButton.setImageResource(
            when{
                pin.emotion >= 5 -> R.drawable.selected_button_scared
                pin.emotion >= 4 -> R.drawable.selected_button_bad
                pin.emotion >= 3 -> R.drawable.selected_button_neutral
                pin.emotion >= 2 -> R.drawable.selected_button_good
                else -> R.drawable.selected_button_verygood
            }
        )


        // likeButton click event
        val likeButton = dialogView.findViewById<ImageButton>(R.id.likeButton)
        val likeCountText = dialogView.findViewById<TextView>(R.id.likeCountText)

        var likeCount = 0

        likeCountText.text = likeCount.toString()

        likeButton.setOnClickListener {
            val currentImageDrawable = likeButton.drawable

            // 'unselected_like_button' -> 'selected_like_button'
            if (currentImageDrawable.constantState == resources.getDrawable(R.drawable.unselected_button_pin_like).constantState) {
                likeButton.setImageResource(R.drawable.selected_button_pin_like)
                likeCount++ // 좋아요 수 증가
            }
            // 'selected_like_button'-> 'unselected_like_button'
            else if (currentImageDrawable.constantState == resources.getDrawable(R.drawable.selected_button_pin_like).constantState) {
                likeButton.setImageResource(R.drawable.unselected_button_pin_like)
                if (likeCount > 0) {
                    likeCount--
                }
            }

            // update likeButton
            likeCountText.text = likeCount.toString()
        }



    }
}