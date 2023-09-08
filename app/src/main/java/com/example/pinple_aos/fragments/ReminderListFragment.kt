package com.example.pinple_aos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pinple_aos.R
import com.example.pinple_aos.ReminderItem
import com.example.pinple_aos.ReminderAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pinple_aos.Retrofit2.RetrofitClient
import com.example.pinple_aos.Retrofit2.apiInterfaceTori
import retrofit2.Callback
import com.example.pinple_aos.Retrofit2.ReminderResponse
import com.example.pinple_aos.toReminderItem
import retrofit2.Call
import retrofit2.Response

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
        val reminderAdapter = ReminderAdapter(itemList)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_board)
        recyclerView.adapter = reminderAdapter

        val countReminderTextView = view.findViewById<TextView>(R.id.countReminderTextView)

        // Retrofit 클라이언트 생성
        val apiService = RetrofitClient.getClient()?.create(apiInterfaceTori::class.java)

        // API 호출
        apiService?.getReminder("12345")?.enqueue(object : Callback<ReminderResponse> {
            override fun onResponse(
                call: Call<ReminderResponse>,
                response: Response<ReminderResponse>
            ) {
                if (response.isSuccessful) {
                    val reminderResponse = response.body()
                    if (reminderResponse != null) {
                        val notifications = reminderResponse.result
                        val reminders = notifications.map { it.toReminderItem() }
                        itemList.addAll(reminders)
                        reminderAdapter.notifyDataSetChanged()

                        val notificationCount = reminders.size
                        countReminderTextView.text = notificationCount.toString()

                        Log.d("response : ", response?.body().toString())
                        println(response?.body()?.isSuccess)
                        println(response?.body()?.code)
                        println(response?.body()?.message)
                    }
                } else {
                    // API 호출 실패 처리
                    Log.e("API Failure", "API 호출이 실패했습니다. HTTP 상태 코드: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ReminderResponse>, t: Throwable) {
                // 네트워크 오류 처리
                Log.e("Network Error", "네트워크 오류가 발생했습니다. 에러 메시지: ${t.message}")
            }
        })

        // val itemList = ArrayList<ReminderItem>()

        // itemList.add(ReminderItem("반포한강공원","2023년 5월 28일 (일) 17:00"))
        // itemList.add(ReminderItem("반포한강공원","2023년 5월 28일 (일) 17:00"))

        // val reminderAdapter = ReminderAdapter(itemList)

        // val recyclerView = view.findViewById<RecyclerView>(R.id.rv_board)
        // recyclerView.adapter = reminderAdapter

    }

}


