package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pinple_aos.*

class ReminderRecordFragment : Fragment() {

    companion object {
        fun newInstance(): ReminderRecordFragment {
            val args = Bundle()
            val fragment = ReminderRecordFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder_record, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = ArrayList<ReminderRecordItem>()

        itemList.add(ReminderRecordItem("반포한강공원","2023년 5월 28일 (일) 17:00", "보통", R.drawable.circle_status_middle, R.color.light_bg_middle, R.color.color_status_middle))
        itemList.add(ReminderRecordItem("이촌한강공원","2023년 5월 28일 (일) 17:00", "약간 혼잡", R.drawable.circle_status_many, R.color.light_bg_many, R.color.color_status_many))
        itemList.add(ReminderRecordItem("이촌한강공원","2023년 5월 28일 (일) 17:00","혼잡", R.drawable.circle_status_toomany, R.color.light_bg_toomany, R.color.color_status_toomany))

        val reminderRecordAdapter = ReminderRecordAdapter(itemList)

        val recyclerView = view.findViewById<RecyclerView>(R.id.RecordRV_board)
        recyclerView.adapter = reminderRecordAdapter

    }
}
