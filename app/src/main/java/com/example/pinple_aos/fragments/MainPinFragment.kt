package com.example.pinple_aos.fragments

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
import android.text.Editable
import androidx.core.content.res.ResourcesCompat
import android.view.View.INVISIBLE
import android.view.View.VISIBLE


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

        // writePinButton를 누를 때 write_pin_bottom_sheet.xml을 보여줌
        binding.writePinButton.setOnClickListener {
            showWritePinBottomSheet()
            bottomSheetIsVisible = true

            if (bottomSheetIsVisible) { // bottomSheet가 보이는 경우
                binding.pinViewButton.visibility = View.INVISIBLE
                binding.currentLocationView.visibility = View.VISIBLE
            } else { // bottomSheet가 보이지 않는 경우
                binding.pinViewButton.visibility = View.VISIBLE
                binding.currentLocationView.visibility = View.INVISIBLE
            }

            val pinIcon = ResourcesCompat.getDrawable(resources, R.drawable.icon_user_location, null)
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
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(dialogView)
        bottomSheetIsVisible = true

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

    // 바텀시트가 사라졌을 때 호출되는 함수
    private fun onBottomSheetDismissed() {
        bottomSheetIsVisible = false
        binding.pinViewButton.visibility = View.VISIBLE
        binding.currentLocationView.visibility = View.INVISIBLE
    }


    private val bottomSheetDialogs: MutableList<BottomSheetDialog> = mutableListOf()
    private var bottomSheetIsVisible = false

    private fun showWritePinBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.write_pin_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(dialogView)
        bottomSheetDialog.show()

        val unselectPinVeryClearButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonVeryClear)
        val unselectPinClearButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonClear)
        val unselectPinMiddleButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonMiddle)
        val unselectPinManyButton = dialogView.findViewById<ImageButton>(R.id.unselectedButtonMany)
        val unselectPinTooManyButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonTooMany)

        val unselectedNextButton = dialogView.findViewById<ImageButton>(R.id.unselectedNextButton)

        val imageButtons = listOf(
            unselectPinVeryClearButton,
            unselectPinClearButton,
            unselectPinMiddleButton,
            unselectPinManyButton,
            unselectPinTooManyButton
        )
        var selectedButton: ImageButton? = null

        imageButtons.forEach { button ->
            button.setOnClickListener {
                if (button == selectedButton) {
                    // 이미 선택된 버튼을 다시 클릭한 경우 선택 해제
                    selectedButton = null
                    button.isSelected = false
                } else {
                    // 새로운 버튼 선택
                    selectedButton?.isSelected = false
                    selectedButton = button
                    button.isSelected = true
                }

                // 이미지 변경
                imageButtons.forEach { btn ->
                    val newImageResource = if (btn.isSelected) {
                        when (btn) {
                            unselectPinVeryClearButton -> R.drawable.selected_button_veryclear
                            unselectPinClearButton -> R.drawable.selected_button_clear
                            unselectPinMiddleButton -> R.drawable.selected_button_middle
                            unselectPinManyButton -> R.drawable.selected_button_many
                            unselectPinTooManyButton -> R.drawable.selected_button_toomany
                            else -> R.drawable.unselected_button_veryclear
                        }
                    } else {
                        when (btn) {
                            unselectPinVeryClearButton -> R.drawable.unselected_button_veryclear
                            unselectPinClearButton -> R.drawable.unselected_button_clear
                            unselectPinMiddleButton -> R.drawable.unselected_button_middle
                            unselectPinManyButton -> R.drawable.unselected_button_many
                            unselectPinTooManyButton -> R.drawable.unselected_button_toomany
                            else -> R.drawable.unselected_button_veryclear
                        }
                    }
                    btn.setImageResource(newImageResource)

                    if (selectedButton == null) {
                        // unselected_next_button 이미지를 원래대로 변경하는 로직
                        unselectedNextButton.setImageResource(R.drawable.unselected_next_button)
                        unselectedNextButton.setOnClickListener(null) // 선택된 버튼이 없을 때 클릭 이벤트 해제
                    } else {
                        // unselected_next_button 이미지를 button_next로 변경하는 로직
                        unselectedNextButton.setImageResource(R.drawable.button_next)
                        unselectedNextButton.setOnClickListener {
                            showStep2BottomSheet()
                            bottomSheetIsVisible = true

                            if (!bottomSheetIsVisible) {
                                binding.pinViewButton.visibility = View.INVISIBLE
                                binding.currentLocationView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }

        // 바텀시트가 사라질 때
        bottomSheetDialog.setOnDismissListener {
            onBottomSheetDismissed()
        }

        bottomSheetDialogs.add(bottomSheetDialog)
    }

    private fun showStep2BottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.write_pin_step2_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(dialogView)
        bottomSheetDialog.show()

        val unselectPinVeryGoodButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonVeryGood)
        val unselectPinGoodButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonGood)
        val unselectPinNeutralButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonNeutral)
        val unselectPinBadButton = dialogView.findViewById<ImageButton>(R.id.unselectedButtonBad)
        val unselectPinScaredButton =
            dialogView.findViewById<ImageButton>(R.id.unselectedButtonScared)

        val unselectedNextButton = dialogView.findViewById<ImageButton>(R.id.unselectedNextButton)

        val imageButtons = listOf(
            unselectPinVeryGoodButton,
            unselectPinGoodButton,
            unselectPinNeutralButton,
            unselectPinBadButton,
            unselectPinScaredButton
        )
        var selectedButton: ImageButton? = null

        imageButtons.forEach { button ->
            button.setOnClickListener {
                if (button == selectedButton) {
                    // 이미 선택된 버튼을 다시 클릭한 경우 선택 해제
                    selectedButton = null
                    button.isSelected = false
                } else {
                    // 새로운 버튼 선택
                    selectedButton?.isSelected = false
                    selectedButton = button
                    button.isSelected = true
                }

                // 이미지 변경
                imageButtons.forEach { btn ->
                    val newImageResource = if (btn.isSelected) {
                        when (btn) {
                            unselectPinVeryGoodButton -> R.drawable.selected_button_verygood
                            unselectPinGoodButton -> R.drawable.selected_button_good
                            unselectPinNeutralButton -> R.drawable.selected_button_neutral
                            unselectPinBadButton -> R.drawable.selected_button_bad
                            unselectPinScaredButton -> R.drawable.selected_button_scared
                            else -> R.drawable.unselected_button_verygood
                        }
                    } else {
                        when (btn) {
                            unselectPinVeryGoodButton -> R.drawable.unselected_button_verygood
                            unselectPinGoodButton -> R.drawable.unselected_button_good
                            unselectPinNeutralButton -> R.drawable.unselected_button_neutral
                            unselectPinBadButton -> R.drawable.unselected_button_bad
                            unselectPinScaredButton -> R.drawable.unselected_button_scared
                            else -> R.drawable.unselected_button_verygood
                        }
                    }
                    btn.setImageResource(newImageResource)

                    if (selectedButton == null) {
                        // unselected_next_button 이미지를 원래대로 변경하는 로직
                        unselectedNextButton.setImageResource(R.drawable.unselected_next_button)
                        unselectedNextButton.setOnClickListener(null) // 선택된 버튼이 없을 때 클릭 이벤트 해제
                    } else {
                        // unselected_next_button 이미지를 button_next로 변경하는 로직
                        unselectedNextButton.setImageResource(R.drawable.button_next)
                        unselectedNextButton.setOnClickListener {
                            showStep3BottomSheet()
                            bottomSheetIsVisible = true

                            if (!bottomSheetIsVisible) {
                                binding.pinViewButton.visibility = View.INVISIBLE
                                binding.currentLocationView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
        bottomSheetDialogs.add(bottomSheetDialog)
    }

    private fun showStep3BottomSheet(){
        val dialogView = layoutInflater.inflate(R.layout.write_pin_step3_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(dialogView)
        bottomSheetDialog.show()

        val editText = dialogView.findViewById<EditText>(R.id.pinEditTextView)
        val textCounter = dialogView.findViewById<TextView>(R.id.textCounterText)


        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                textCounter.text = "0 / 200자"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var userinput = editText.text.toString()
                textCounter.text = userinput.length.toString() + " / 200자"
            }

            override fun afterTextChanged(s: Editable?) {
                var userinput = editText.text.toString()
                textCounter.text = userinput.length.toString() + " / 200자"
            }
        })

        val doneWritePinButton = dialogView.findViewById<ImageButton>(R.id.doneWritePinButton)

        doneWritePinButton.setOnClickListener {
            showCautionBottomSheet()
            bottomSheetIsVisible = true

            if (!bottomSheetIsVisible) {
                binding.pinViewButton.visibility = View.INVISIBLE
                binding.currentLocationView.visibility = View.VISIBLE
            }
        }

        bottomSheetDialogs.add(bottomSheetDialog)
    }

    private fun showCautionBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.write_pin_caution_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.cautionBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(dialogView)
        bottomSheetDialog.show()
        bottomSheetIsVisible = true

        val checkButton = dialogView.findViewById<ImageButton>(R.id.pinCheckButton)
        val uploadButton = dialogView.findViewById<ImageButton>(R.id.pinUploadButton)

        checkButton.setOnClickListener {
            bottomSheetDialog.dismiss()
            bottomSheetIsVisible = true

            if (!bottomSheetIsVisible) {
                binding.pinViewButton.visibility = View.INVISIBLE
                binding.currentLocationView.visibility = View.VISIBLE
            }
        }

        uploadButton.setOnClickListener {
            bottomSheetDialog.dismiss()
            bottomSheetIsVisible = false


            if (!bottomSheetIsVisible) {
                binding.pinViewButton.visibility = View.VISIBLE
                binding.currentLocationView.visibility = View.INVISIBLE
            }

            bottomSheetDialogs.forEach { it.dismiss() }
            bottomSheetDialogs.clear()
        }
    }










}