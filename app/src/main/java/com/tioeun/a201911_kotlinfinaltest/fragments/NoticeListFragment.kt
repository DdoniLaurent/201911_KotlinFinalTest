package com.tioeun.a201911_kotlinfinaltest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tioeun.a20191119_01_banklistpractice.adapters.NoticeAdapter
import com.tioeun.a201911_kotlinfinaltest.R
import com.tioeun.a201911_kotlinfinaltest.datas.Notice
import com.tioeun.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.fragment_notice_list.*
import org.json.JSONObject

class NoticeListFragment : BaseFragment() {

    var noticeList = ArrayList<Notice>()
    var noticeAdapter:NoticeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notice_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = activity

        setupEvents()
        setValue()
    }

    override fun setupEvents() {

    }

    override fun setValue() {
        noticeAdapter = NoticeAdapter(mContext!!, noticeList)
        noticeListView.adapter = noticeAdapter

        getNoticeFromServer()
    }

    fun getNoticeFromServer() {
        ServerUtil.getRequestNoticeList(mContext!!, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("공지목록응답", json.toString())

                var code = json.getInt("code")
                if(code == 200) {
                    val data = json.getJSONObject("data")
                    val notices = data.getJSONArray("notices")

                    noticeList.clear()

                    for(i in 0..notices.length()-1) {
                        noticeList.add(Notice.getNoticeFromJson(notices.getJSONObject(i)))
                    }

                    activity!!.runOnUiThread {
                        noticeAdapter?.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(mContext!!, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

}