package com.example.pinple_aos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pinple_aos.R
import com.example.pinple_aos.ReminderItem
import com.example.pinple_aos.ReminderAdapter
import androidx.recyclerview.widget.RecyclerView

class ReminderListFragment : Fragment() {

    companion object {
        fun newInstance(): ReminderListFragment {
            val args = Bundle()
            val fragment = ReminderListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_reminder_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = ArrayList<ReminderItem>()

        itemList.add(ReminderItem("반포한강공원","2023년 5월 28일 (일) 17:00"))
        itemList.add(ReminderItem("반포한강공원","2023년 5월 28일 (일) 17:00"))

        val reminderAdapter = ReminderAdapter(itemList)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_board)
        recyclerView.adapter = reminderAdapter

    }
}


